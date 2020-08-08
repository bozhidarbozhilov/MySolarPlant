package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;
import com.bozhilov.mysolarplant.services.services.ChargeControllerService;
import com.bozhilov.mysolarplant.web.models.AllControllersViewModel;
import com.bozhilov.mysolarplant.web.models.ChargeControllerCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/controllers")
public class ChargeControllerController extends BaseController{
    private final ChargeControllerService controllerService;
    private final ModelMapper mapper;

    @Autowired
    public ChargeControllerController(ChargeControllerService controllerService, ModelMapper mapper) {
        this.controllerService = controllerService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView getCreatePage(@ModelAttribute(name="controllerCreate")
                                              ChargeControllerCreateModel chargeControllerCreateModel,
                                      ModelAndView modelAndView){
        modelAndView.setViewName(super.view("controllers/controller-create"));
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createBattery(@ModelAttribute(name="controllerCreate")
                                                  ChargeControllerCreateModel controllerCreateModel,
                                      BindingResult bindingResult, ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("controllers/controller-create"));
        }else {
            ChargeControllerServiceModel controllerServiceModel = mapper.map(controllerCreateModel,
                    ChargeControllerServiceModel.class);
            controllerService.saveController(controllerServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public Object allContollers(){
        return controllerService
                .allControllers()
                .stream()
                .map(controller->mapper.map(controller, AllControllersViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

}
