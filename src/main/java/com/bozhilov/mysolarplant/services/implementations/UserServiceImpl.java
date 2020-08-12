package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.users.User;
import com.bozhilov.mysolarplant.data.repositories.UserRepository;
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

import static com.bozhilov.mysolarplant.utils.Constants.INVALID_USERS_PROPERTIES;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        if(userRepository.count()==0){
            user.setAuthorities(this.roleService.findAll());
        }else{
            user.setAuthorities(this.roleService.findRoleByName(Constants.ROLE_USER_NAME));
        }
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return modelMapper.map(user, UserServiceModel.class);
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
