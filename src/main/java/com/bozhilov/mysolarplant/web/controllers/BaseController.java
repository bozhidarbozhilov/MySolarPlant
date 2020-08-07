package com.bozhilov.mysolarplant.web.controllers;

public class BaseController {

    public String view(String viewName) {
        return "views/"+viewName;
    }
    public String redirect(String url) {
        return "redirect:/" + url;
    }

}
