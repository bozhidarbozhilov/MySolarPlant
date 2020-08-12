package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.web.models.RegisterUserModel;
import com.fasterxml.jackson.databind.ser.Serializers;
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

import javax.validation.Valid;
import java.io.InvalidObjectException;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView getRegisterPage(@ModelAttribute(name="registerUser")RegisterUserModel registerUserModel,
                                        ModelAndView modelAndView){
        modelAndView.setViewName(super.view("users/user-register"));
        return modelAndView;
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerUser(@Valid @ModelAttribute(name="registerUser")RegisterUserModel registerUserModel,
                                     BindingResult bindingResult, ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("users/user-register"));

        }else{
            UserServiceModel userServiceModel = modelMapper.map(registerUserModel, UserServiceModel.class);
            userService.registerUser(userServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

}
