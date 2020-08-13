package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.UserProfileServiceModel;

import java.io.InvalidObjectException;

public interface UserProfileService {
    UserProfileServiceModel createProfile(UserProfileServiceModel userProfileServiceModel) throws InvalidObjectException;
    UserProfileServiceModel editProfile(UserProfileServiceModel userProfileServiceModel, String username) throws InvalidObjectException;
    void deleteProfile(String id);
    UserProfileServiceModel findUserProfileById(String id);
    UserProfileServiceModel findUserProfileByUsername(String username);
}
