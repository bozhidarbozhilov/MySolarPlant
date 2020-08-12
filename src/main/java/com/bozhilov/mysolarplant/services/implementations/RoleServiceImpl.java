package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.users.Role;
import com.bozhilov.mysolarplant.data.repositories.RoleRepository;
import com.bozhilov.mysolarplant.services.services.RoleService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if(this.roleRepository.count()==0){
            Role admin = new Role(Constants.ROLE_ADMIN_NAME);
            Role employee=new Role(Constants.ROLE_ENGINEER_NAME);
            Role user = new Role(Constants.ROLE_USER_NAME);

            this.roleRepository.save(admin);
            this.roleRepository.save(employee);
            this.roleRepository.save(user);
        }
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(this.roleRepository.findAll());
    }

    @Override
    public Set<Role> findRoleByName(String authorityName) {
        return this.roleRepository
                .findAll()
                .stream()
                .filter(role -> role.getAuthority().equals(authorityName))
                .collect(Collectors.toSet());
    }
}
