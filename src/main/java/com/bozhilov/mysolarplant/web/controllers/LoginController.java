package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {

    @PageTitle("Login")
    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName(super.view("users/user-login"));
        return modelAndView;
    }
}
