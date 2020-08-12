package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.data.models.users.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findAll();
    Set<Role> findRoleByName(String authorityName);
}
