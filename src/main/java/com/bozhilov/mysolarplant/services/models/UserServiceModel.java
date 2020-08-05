package com.bozhilov.mysolarplant.services.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.bozhilov.mysolarplant.utils.Constants.USERNAME_MAX_LENGTH;
import static com.bozhilov.mysolarplant.utils.Constants.USERNAME_MIN_LENGTH;

public class UserServiceModel {
    private String id;
    private String username;
    private String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @Size(min=USERNAME_MIN_LENGTH,
            max = USERNAME_MAX_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
