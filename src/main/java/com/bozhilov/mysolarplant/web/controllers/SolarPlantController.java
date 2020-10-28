package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.services.SolarPlantService;
import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import com.bozhilov.mysolarplant.web.models.SolarPlantAllViewModel;
import com.bozhilov.mysolarplant.web.models.SolarPlantCreateModel;
import com.bozhilov.mysolarplant.web.models.SolarPlantDetailsModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/solar_plants")
public class SolarPlantController extends BaseController{
    private final SolarPlantService solarPlantService;
    private final ModelMapper mapper;

    public SolarPlantController(SolarPlantService solarPlantService, ModelMapper mapper) {
        this.solarPlantService = solarPlantService;
        this.mapper = mapper;
    }

    @PageTitle("Solar Plant Create")
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
            //SolarPlantServiceModel solarPlantServiceModel = mappingUtils.solarPlantCreateToService(solarPlantCreateModel);
            //solarPlantService.createSolarPlant(solarPlantServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

    @GetMapping(value="/all-for-user", produces = "application/json")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public Object allSolarPlantsByUsername(Principal principal,
                                          ModelAndView modelAndView){
        return solarPlantService.findAllByUsername(principal.getName())
                .stream()
                .map(solarPlant-> mapper.map(solarPlant, SolarPlantAllViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @PageTitle("Solar Plant Details")
    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView plantDetailsPage(@PathVariable(name="id") String id,ModelAndView modelAndView){
        SolarPlantServiceModel solarPlantServiceModel = solarPlantService.findById(id);
        SolarPlantDetailsModel solarPlantDetailsModel = mapper.map(solarPlantServiceModel, SolarPlantDetailsModel.class);
        modelAndView.addObject("solarPlantDetails", solarPlantDetailsModel);
        modelAndView.setViewName(super.view("solar-plants/solar-plant-details"));

        return modelAndView;
    }

    @PageTitle("All Solar Plant")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllPlants(ModelAndView modelAndView){
        List<SolarPlantAllViewModel> allPlants =
                solarPlantService.findAll()
                    .stream()
                    .map(solarPlantServiceModel -> mapper
                            .map(solarPlantServiceModel, SolarPlantAllViewModel.class))
                    .collect(Collectors.toUnmodifiableList());
        modelAndView.addObject("plants", allPlants);
        modelAndView.setViewName(super.view("solar-plants/all-solar-plants"));
        return modelAndView;
    }

}
