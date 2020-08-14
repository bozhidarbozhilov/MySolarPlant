package com.bozhilov.mysolarplant.web.models;


import com.bozhilov.mysolarplant.services.models.RoleServiceModel;

import java.util.HashSet;
import java.util.Set;

public class AllUsersViewModel extends BaseViewModel{
    private String username;
    private Set<RoleViewModel> authorities;

    public AllUsersViewModel(){
        authorities = new HashSet<>();
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<RoleViewModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<RoleViewModel> authorities) {
        this.authorities = authorities;
    }
}
