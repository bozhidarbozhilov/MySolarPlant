package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;


public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel) throws InvalidObjectException;
    UserServiceModel findByUsername(String username);
    List<UserServiceModel> findAllUsers();
    void addRole(String id) throws InvalidObjectException;
    void changeRole(String id) throws InvalidObjectException;
    void removeRole(String id, String roleName) throws InvalidObjectException;
    boolean isUsernameTaken(String username);
}
