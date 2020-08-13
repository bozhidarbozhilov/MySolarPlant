package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.UserProfileServiceModel;
import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.services.services.UserProfileService;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import com.bozhilov.mysolarplant.web.models.UserProfileCreateModel;
import com.bozhilov.mysolarplant.web.models.UserProfileDetailsModel;
import com.bozhilov.mysolarplant.web.models.UserProfileEditModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;
import java.security.Principal;

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

    @PageTitle("User Create")
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
                                          Principal principal,
                                          ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("users/user-profile-create"));
        }else{
            UserProfileServiceModel profileServiceModel = mapper
                    .map(userProfileCreateModel, UserProfileServiceModel.class);
            profileServiceModel.setUser(new UserServiceModel());
            profileServiceModel.getUser().setUsername(principal.getName());
            userProfileService.createProfile(profileServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

    @PageTitle("User Edit")
    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getBatteryEditPage(Principal principal,
                                           ModelAndView modelAndView){

        UserProfileServiceModel  foundUserProfile = userProfileService.findUserProfileByUsername(principal.getName());
        UserProfileEditModel profileForEdit = mapper.map(foundUserProfile, UserProfileEditModel.class);
        modelAndView.addObject("profileEditModel", profileForEdit);
        modelAndView.setViewName(super.view("users/user-profile-edit"));
        return modelAndView;
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editUserProfile(@ModelAttribute("profileEditModel") UserProfileEditModel profileEditModel,
                                    BindingResult bindingResult,
                                    Principal principal,
                                    ModelAndView modelAndView) throws InvalidObjectException {

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("users/user-profile-edit");
        }else {
            UserProfileServiceModel userProfileServiceModel = mapper.map(profileEditModel, UserProfileServiceModel.class);
            UserProfileServiceModel editedUserProfile = userProfileService.editProfile(userProfileServiceModel, principal.getName());
            modelAndView.addObject("profileEditModel" ,
                    mapper.map(editedUserProfile, UserProfileEditModel.class));
            modelAndView.setViewName(view("users/user-profile-edit"));
        }
        return modelAndView;
    }

    @PageTitle("User Details")
    @GetMapping("/details")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profileDetailsPage(Principal principal,
                                           ModelAndView modelAndView){
        UserProfileServiceModel profile = userProfileService.findUserProfileByUsername(principal.getName());

        UserProfileDetailsModel userProfileDetailsModel = mapper.map(profile, UserProfileDetailsModel.class);

        modelAndView.addObject("profileDetailsModel", userProfileDetailsModel);

        modelAndView.setViewName(super.view("users/user-profile-details"));

        return modelAndView;
    }


}
