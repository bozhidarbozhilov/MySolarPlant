package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.users.Role;
import com.bozhilov.mysolarplant.data.repositories.RoleRepository;
import com.bozhilov.mysolarplant.services.models.RoleServiceModel;
import com.bozhilov.mysolarplant.services.services.RoleService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() {
        if(this.roleRepository.count()==0){
            Role admin = new Role(Constants.ROLE_ADMIN_NAME);
            Role engineer=new Role(Constants.ROLE_ENGINEER_NAME);
            Role user = new Role(Constants.ROLE_USER_NAME);

            this.roleRepository.save(admin);
            this.roleRepository.save(engineer);
            this.roleRepository.save(user);
        }
    }

    @Override
    public Set<RoleServiceModel> findAll() {

        return this.roleRepository.findAll()
                .stream()
                .map(role ->mapper.map(role, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findRoleByName(String authorityName) {
        return mapper.map(roleRepository.findRoleByAuthority(authorityName), RoleServiceModel.class);
    }
}
