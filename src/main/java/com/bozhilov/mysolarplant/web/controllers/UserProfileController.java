package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.UserProfileServiceModel;
import com.bozhilov.mysolarplant.services.services.UserProfileService;
import com.bozhilov.mysolarplant.web.models.UserProfileCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;

@Controller
@RequestMapping("/users-profile")
public class UserProfileController extends BaseController{
    private final UserProfileService userProfileService;
    private final ModelMapper mapper;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, ModelMapper mapper) {
        this.userProfileService = userProfileService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getProfileCreatePage(@ModelAttribute(name="createProfileModel")
                                                         UserProfileCreateModel userProfileCreateModel,
                                                         ModelAndView modelAndView){
        modelAndView.setViewName(super.view("users/user-profile-create"));
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView createUserProfile(@ModelAttribute(name="createProfileModel")
                                                      UserProfileCreateModel userProfileCreateModel,
                                          BindingResult bindingResult,
                                          ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("users/user-profile-create"));
        }else{
            UserProfileServiceModel profileServiceModel = mapper
                    .map(userProfileCreateModel, UserProfileServiceModel.class);
            userProfileService.createProfile(profileServiceModel);
            modelAndView.setViewName(super.redirect("/home"));
        }
        return modelAndView;
    }

}
