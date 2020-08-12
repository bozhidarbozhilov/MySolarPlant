package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.services.MappingService;
import com.bozhilov.mysolarplant.services.services.SolarPlantService;
import com.bozhilov.mysolarplant.web.models.SolarPlantCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;
import java.security.Principal;

@Controller
@RequestMapping("/solar_plants")
public class SolarPlantController extends BaseController{
    private final SolarPlantService solarPlantService;
    private final MappingService mappingService;
    private final ModelMapper mapper;

    public SolarPlantController(SolarPlantService solarPlantService, MappingService mappingService, ModelMapper mapper) {
        this.solarPlantService = solarPlantService;
        this.mappingService = mappingService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getPlantCreatePage(@ModelAttribute(name="solarPlantCreate")
                                                   SolarPlantCreateModel solarPlantCreateModel,
                                           ModelAndView modelAndView){
        modelAndView.setViewName(super.view("/solar-plants/solar-plant-create"));

        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createSolarPlant(@ModelAttribute(name="solarPlantCreate") SolarPlantCreateModel solarPlantCreateModel,
                                         BindingResult bindingResult, Principal principal,
                                         ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("solar-plants/solar-plant-create"));
        }else {
            solarPlantCreateModel.setUsername(principal.getName());
            SolarPlantServiceModel solarPlantServiceModel = mappingService.solarPlantCreateToService(solarPlantCreateModel);
            solarPlantService.createSolarPlant(solarPlantServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

}
