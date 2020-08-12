package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.users.UserProfile;
import com.bozhilov.mysolarplant.data.repositories.UserProfileRepository;
import com.bozhilov.mysolarplant.services.models.UserProfileServiceModel;
import com.bozhilov.mysolarplant.services.services.UserProfileService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, ModelMapper mapper, Validator validator) {
        this.userProfileRepository = userProfileRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public UserProfileServiceModel createProfile(UserProfileServiceModel userProfileServiceModel) throws InvalidObjectException {
        if(validator.validate(userProfileServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_USER_PROFILE_PROPERTIES);
        }
        UserProfile profileForSave = mapper.map(userProfileServiceModel, UserProfile.class);
        UserProfile savedProfile = userProfileRepository.save(profileForSave);
        return mapper.map(savedProfile, UserProfileServiceModel.class);
    }

    @Override
    public UserProfileServiceModel editProfile(UserProfileServiceModel userProfileServiceModel) throws InvalidObjectException {
        if(validator.validate(userProfileServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_USER_PROFILE_PROPERTIES);
        }
        UserProfile profileForEdit = mapper.map(userProfileServiceModel, UserProfile.class);
        UserProfile editedProfile = userProfileRepository.saveAndFlush(profileForEdit);
        return mapper.map(editedProfile, UserProfileServiceModel.class);
    }

    @Override
    public void deleteProfile(String id) {
        UserProfile foundProfile = userProfileRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException(Constants.USER_PROFILE_NOT_FOUND));
        userProfileRepository.delete(foundProfile);
    }
}
