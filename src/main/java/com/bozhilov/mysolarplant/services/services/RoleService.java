package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.data.models.users.Role;
import com.bozhilov.mysolarplant.services.models.RoleServiceModel;

import java.util.Set;

public interface RoleService {
    Set<RoleServiceModel> findAll();
    RoleServiceModel findRoleByName(String authorityName);
}
