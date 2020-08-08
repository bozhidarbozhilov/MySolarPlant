package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.InverterServiceModel;
import com.bozhilov.mysolarplant.services.services.InverterService;
import com.bozhilov.mysolarplant.web.models.AllInvertersViewModel;
import com.bozhilov.mysolarplant.web.models.InverterCreateModel;
import com.bozhilov.mysolarplant.web.models.InverterViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/inverters")
public class InverterController extends BaseController{

    private final InverterService inverterService;
    private final ModelMapper mapper;

    public InverterController(InverterService inverterService, ModelMapper mapper) {
        this.inverterService = inverterService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView getInverterCreatePage(@ModelAttribute(name="inverterCreate")
                                              InverterCreateModel inverterCreateModel,
                                              ModelAndView modelAndView){
        modelAndView.setViewName(super.view("inverters/inverter-create"));
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createInverter(@ModelAttribute(name="inverterCreate") InverterCreateModel inverterCreateModel,
                                      BindingResult bindingResult, ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("inverters/inverter-create"));
        }else {
            InverterServiceModel inverterServiceModel = mapper.map(inverterCreateModel, InverterServiceModel.class);
            inverterService.saveInverter(inverterServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public Object getAllInverters(){
        return inverterService
                .allInverters()
                .stream()
                .map(inverterServiceModel -> mapper.map(inverterServiceModel, AllInvertersViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/all_solar_unit", produces = "application/json")
    @ResponseBody
    public Object getAllInvertersForSolarUnit(){
        return inverterService
                .allInverters()
                .stream()
                .map(inverterServiceModel -> mapper.map(inverterServiceModel, InverterViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }
}
