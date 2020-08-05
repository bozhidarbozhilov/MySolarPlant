package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;


public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel) throws InvalidObjectException;

}
