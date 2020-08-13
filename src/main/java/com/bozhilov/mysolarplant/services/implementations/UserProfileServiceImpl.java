package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.users.UserProfile;
import com.bozhilov.mysolarplant.data.repositories.UserProfileRepository;
import com.bozhilov.mysolarplant.services.models.UserProfileServiceModel;
import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.services.services.UserProfileService;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final Validator validator;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserService userService, ModelMapper mapper, Validator validator) {
        this.userProfileRepository = userProfileRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public UserProfileServiceModel createProfile(UserProfileServiceModel userProfileServiceModel) throws InvalidObjectException {
        if(validator.validate(userProfileServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_USER_PROFILE_PROPERTIES);
        }
        UserServiceModel user = userService.findByUsername(userProfileServiceModel.getUser().getUsername());
        userProfileServiceModel.setUser(user);
        UserProfile profileForSave = mapper.map(userProfileServiceModel, UserProfile.class);

        UserProfile savedProfile = userProfileRepository.save(profileForSave);
        return mapper.map(savedProfile, UserProfileServiceModel.class);
    }

    @Override
    public UserProfileServiceModel editProfile(UserProfileServiceModel userProfileServiceModel, String username) throws InvalidObjectException {
        if(validator.validate(userProfileServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_USER_PROFILE_PROPERTIES);
        }
        UserServiceModel user = userService.findByUsername(username);
        userProfileServiceModel.setUser(user);
        UserProfile existingProfile =userProfileRepository
                .findByUsername(username)
                .orElseThrow(()->new IllegalArgumentException(Constants.USER_PROFILE_NOT_FOUND));
        userProfileServiceModel.setId(existingProfile.getId());
        UserProfile profileForEdit = mapper.map(userProfileServiceModel, UserProfile.class);
        UserProfile editedProfile = userProfileRepository.save(profileForEdit);
        return mapper.map(editedProfile, UserProfileServiceModel.class);
    }

    @Override
    public void deleteProfile(String id) {
        UserProfile foundProfile = userProfileRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException(Constants.USER_PROFILE_NOT_FOUND));
        userProfileRepository.delete(foundProfile);
    }

    @Override
    public UserProfileServiceModel findUserProfileById(String id) {
        UserProfile foundProfile = userProfileRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException(Constants.USER_PROFILE_NOT_FOUND));

        return mapper.map(foundProfile, UserProfileServiceModel.class);
    }

    @Override
    public UserProfileServiceModel findUserProfileByUsername(String username) {
        UserProfile foundProfile = userProfileRepository
                .findByUsername(username)
                .orElseThrow(()->new IllegalArgumentException(Constants.USER_PROFILE_NOT_FOUND));

        return mapper.map(foundProfile, UserProfileServiceModel.class);
    }
}
