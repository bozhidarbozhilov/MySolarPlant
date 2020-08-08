package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.data.models.plant.SolarUnit;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.services.services.SolarUnitService;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;

@Controller
@RequestMapping("/solar_units")
public class SolarUnitController extends BaseController{
    private final SolarUnitService solarUnitService;
    private final ModelMapper mapper;

    @Autowired
    public SolarUnitController(SolarUnitService solarUnitService, ModelMapper mapper) {
        this.solarUnitService = solarUnitService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView getSolarUnitCreatePage(@ModelAttribute(name="solarUnitCreate")
                                                           SolarUnitCreateModel solarUnitCreateModel,
                                               ModelAndView modelAndView){
        modelAndView.setViewName(super.view("solar-plants/solar-unit-create"));
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createInverter(@ModelAttribute(name="solarUnitCreate") SolarUnitCreateModel solarUnitCreateModel,
                                       BindingResult bindingResult, ModelAndView modelAndView)  {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("solar-plants/solar-unit-create"));
        }else {
            int b=5;
            SolarUnitServiceModel solarUnitServiceModel = mapper.map(solarUnitCreateModel, SolarUnitServiceModel.class);
            solarUnitService.createSolarUnit(solarUnitServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }
}
