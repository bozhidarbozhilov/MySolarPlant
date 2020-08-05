package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.web.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.InvalidObjectException;
import java.rmi.registry.Registry;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(@ModelAttribute(name="registerUser")RegisterUserModel registerUserModel,
                                        ModelAndView modelAndView){
        modelAndView.setViewName("user-register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@Valid @ModelAttribute(name="registerUser")RegisterUserModel registerUserModel,
                                     BindingResult bindingResult, ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("user-register");

        }else{
            UserServiceModel userServiceModel = modelMapper.map(registerUserModel, UserServiceModel.class);
            userService.registerUser(userServiceModel);
            modelAndView.setViewName("redirect:/home");
        }
        return modelAndView;
    }

}
