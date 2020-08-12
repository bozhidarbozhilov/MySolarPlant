package com.bozhilov.mysolarplant.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController{
    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public String getHomePage(){
        return super.view("home/home");
    }

}
