package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import com.bozhilov.mysolarplant.web.models.AllUsersViewModel;
import com.bozhilov.mysolarplant.web.models.RegisterUserModel;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

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

    @PageTitle("Register")
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

    @GetMapping(value="/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllUsers(ModelAndView modelAndView){
        List<AllUsersViewModel> users = this.userService.findAllUsers().stream()
                .map(userServiceModel ->modelMapper.map(userServiceModel, AllUsersViewModel.class))
                .collect(Collectors.toUnmodifiableList());
        modelAndView.addObject("users", users);
        modelAndView.setViewName(super.view("users/all-users"));
        return modelAndView;
    }

    @GetMapping("/change-role/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView changeUserRole(@PathVariable(name="id") String id,
                                       ModelAndView modelAndView) throws InvalidObjectException {
        userService.changeRole(id);
        modelAndView.setViewName(super.redirect("users/all"));
        return modelAndView;
    }

    @GetMapping("/add-role/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addRoleToUser(@PathVariable(name="id") String id,
                                       ModelAndView modelAndView) throws InvalidObjectException {
        userService.addRole(id);
        modelAndView.setViewName(super.redirect("users/all"));
        return modelAndView;
    }

    @GetMapping("/remove-engineer/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeEngineerRole(@PathVariable(name="id") String id,
                                      ModelAndView modelAndView) throws InvalidObjectException {
        userService.removeRole(id, "Engineer");
        modelAndView.setViewName(super.redirect("users/all"));
        return modelAndView;
    }

    @GetMapping("/remove-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView removeUserRole(@PathVariable(name="id") String id,
                                           ModelAndView modelAndView) throws InvalidObjectException {
        userService.removeRole(id, "User");
        modelAndView.setViewName(super.redirect("users/all"));
        return modelAndView;
    }

}
