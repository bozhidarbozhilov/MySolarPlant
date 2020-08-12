package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.UserProfileServiceModel;

import java.io.InvalidObjectException;

public interface UserProfileService {
    UserProfileServiceModel createProfile(UserProfileServiceModel userProfileServiceModel) throws InvalidObjectException;
    UserProfileServiceModel editProfile(UserProfileServiceModel userProfileServiceModel) throws InvalidObjectException;
    void deleteProfile(String id);
}
