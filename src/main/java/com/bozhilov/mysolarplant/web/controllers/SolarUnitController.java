package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.services.services.MappingService;
import com.bozhilov.mysolarplant.services.services.SolarUnitService;
import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import com.bozhilov.mysolarplant.web.models.SolarUnitAllViewModel;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/solar_units")
public class SolarUnitController extends BaseController{
    private final SolarUnitService solarUnitService;
    private final MappingService mappingService;
    private final ModelMapper mapper;

    @Autowired
    public SolarUnitController(SolarUnitService solarUnitService,
                               MappingService mappingService, ModelMapper mapper) {
        this.solarUnitService = solarUnitService;
        this.mappingService = mappingService;
        this.mapper = mapper;
    }

    @PageTitle("Solar Unit Create")
    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getSolarUnitCreatePage(@ModelAttribute(name="solarUnitCreate")
                                                           SolarUnitCreateModel solarUnitCreateModel,
                                               ModelAndView modelAndView){
        modelAndView.setViewName(super.view("solar-plants/solar-unit-create"));
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createSolarUnit(@ModelAttribute(name="solarUnitCreate") SolarUnitCreateModel solarUnitCreateModel,
                                       BindingResult bindingResult, Principal principal,
                                       ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("solar-plants/solar-unit-create"));
        }else {
            solarUnitCreateModel.setUsername(principal.getName());
            SolarUnitServiceModel solarUnitServiceModel = mappingService.solarUnitViewToService(solarUnitCreateModel);
            solarUnitService.createSolarUnit(solarUnitServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

    @GetMapping(value="/all-for-user", produces = "application/json")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public Object allSolarUnitsByUsername(Principal principal,
                                          ModelAndView modelAndView){
        Object obj = solarUnitService.findAllByUsername(principal.getName())
                .stream()
                .map(solarUnit-> mapper.map(solarUnit, SolarUnitAllViewModel.class))
                .collect(Collectors.toUnmodifiableList());
        return obj;
    }
}
