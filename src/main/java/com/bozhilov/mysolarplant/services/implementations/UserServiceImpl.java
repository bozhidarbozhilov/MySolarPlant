package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.users.Role;
import com.bozhilov.mysolarplant.data.models.users.User;
import com.bozhilov.mysolarplant.data.repositories.UserRepository;
import com.bozhilov.mysolarplant.services.models.RoleServiceModel;
import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.services.services.RoleService;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bozhilov.mysolarplant.utils.Constants.INVALID_USERS_PROPERTIES;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    public final String ADMIN_DENIED_MESSAGE = "Can not change Admin's role.";
    public final String INVALID_OPERATION_MESSAGE = "Invalid operation.";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final BCryptPasswordEncoder passwordEncoder;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           Validator validator, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) throws InvalidObjectException {
        if(validator.validate(userServiceModel).size()>0){
            throw new InvalidObjectException(INVALID_USERS_PROPERTIES);
        }
        if(userRepository.count()==0){
            userServiceModel.setAuthorities(this.roleService.findAll());
        }else{
            userServiceModel.getAuthorities().add(roleService.findRoleByName(Constants.ROLE_USER_NAME));
        }
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .collect(Collectors.toUnmodifiableList());
    }


    @Override
    public void addRole(String id) throws InvalidObjectException {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException(Constants.USER_NOT_FOUND));
        if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))){
            throw new InvalidObjectException(ADMIN_DENIED_MESSAGE);

        }
        if(user.getAuthorities().stream().noneMatch(role -> role.getAuthority().equals("ROLE_USER"))){
            user.getAuthorities()
                    .add(modelMapper.map(roleService.findRoleByName("ROLE_USER"),Role.class));
        }else {
            user.getAuthorities()
                    .add(modelMapper.map(roleService.findRoleByName("ROLE_ENGINEER"),Role.class));
        }
        User saveUser = userRepository.saveAndFlush(user);
    }

    @Override
    public void changeRole(String id) throws InvalidObjectException {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException(Constants.USER_NOT_FOUND));
        if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))){
            throw new InvalidObjectException(ADMIN_DENIED_MESSAGE);
        }
        if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_USER"))){
            user.getAuthorities().clear();
            user.getAuthorities()
                    .add(modelMapper.map(roleService.findRoleByName("ROLE_ENGINEER"),Role.class));
        }else {
            user.getAuthorities().clear();
            user.getAuthorities()
                    .add(modelMapper.map(roleService.findRoleByName("ROLE_USER"),Role.class));
        }
       userRepository.saveAndFlush(user);
    }

    @Override
    public void removeRole(String id, String roleName) throws InvalidObjectException {
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException(Constants.USER_NOT_FOUND));
        if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))){
            throw new InvalidObjectException(ADMIN_DENIED_MESSAGE);
        }
        if(roleName.equals("User")){
            if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_USER"))){
                user.getAuthorities().clear();
                user.getAuthorities().add(modelMapper.map(roleService.findRoleByName("ROLE_ENGINEER"),Role.class));
            }else {
                throw new InvalidObjectException(INVALID_OPERATION_MESSAGE);
            }
        } else {
            if(user.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ENGINEER"))){
                user.getAuthorities().clear();
                user.getAuthorities().add(modelMapper.map(roleService.findRoleByName("ROLE_USER"),Role.class));
            }else {
                throw new InvalidObjectException(INVALID_OPERATION_MESSAGE);
            }

        }
        userRepository.saveAndFlush(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails result = userRepository.findByUsername(username);
        if(result==null){
            throw new UsernameNotFoundException(Constants.USER_NOT_FOUND);
        }
        return result;
    }
}
