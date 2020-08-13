package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.InverterServiceModel;
import com.bozhilov.mysolarplant.services.services.InverterService;
import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import com.bozhilov.mysolarplant.web.models.AllInvertersViewModel;
import com.bozhilov.mysolarplant.web.models.InverterCreateModel;
import com.bozhilov.mysolarplant.web.models.InverterEditModel;
import com.bozhilov.mysolarplant.web.models.InverterViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PageTitle("Inverter create")
    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView getInverterCreatePage(@ModelAttribute(name="inverterCreate")
                                              InverterCreateModel inverterCreateModel,
                                              ModelAndView modelAndView){
        modelAndView.setViewName(super.view("inverters/inverter-create"));
        return modelAndView;
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
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
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public Object getAllInverters(){
        return inverterService
                .allInverters()
                .stream()
                .map(inverterServiceModel -> mapper.map(inverterServiceModel, AllInvertersViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping(value = "/all_solar_unit", produces = "application/json")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public Object getAllInvertersForSolarUnit(){
        return inverterService
                .allInverters()
                .stream()
                .map(inverterServiceModel -> mapper.map(inverterServiceModel, InverterViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @PageTitle("Inverter edit")
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView getInverterEditPage(@PathVariable("id") String id,
                                           ModelAndView modelAndView){
        InverterServiceModel  foundInverter = inverterService.findInverterById(id);
        InverterEditModel inverterForEdit = mapper.map(foundInverter, InverterEditModel.class);
        modelAndView.addObject("inverterEditModel", inverterForEdit);
        modelAndView.setViewName(super.view("inverters/inverter-edit"));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView editInverter(@ModelAttribute("inverterEditModel") InverterEditModel inverterEditModel,
                                    BindingResult bindingResult, @PathVariable("id") String id,
                                    ModelAndView modelAndView) throws InvalidObjectException {

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("inverters/inverter-edit");
        }else {
            InverterServiceModel inverterServiceModel = mapper.map(inverterEditModel, InverterServiceModel.class);
            InverterServiceModel editedInverter = inverterService.editInverter(inverterServiceModel);
            modelAndView.addObject("inverterEditModel" ,
                    mapper.map(editedInverter, InverterEditModel.class));
            modelAndView.setViewName(view("inverters/inverter-edit"));
        }
        return modelAndView;
    }

    @PageTitle("Inverter delete")
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView getInverterDeletePage(@PathVariable("id") String id,
                                             ModelAndView modelAndView){
        InverterServiceModel inverterForEdit = inverterService.findInverterById(id);
        InverterViewModel inverterViewModel = mapper.map(inverterForEdit, InverterViewModel.class);
        modelAndView.addObject("inverterDeleteModel", inverterViewModel);
        modelAndView.setViewName(super.view("inverters/inverter-delete"));
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView deleteInverter(@PathVariable("id") String id,
                                      ModelAndView modelAndView){
        inverterService.deleteInverter(id);
        modelAndView.setViewName(super.redirect("home"));
        return modelAndView;
    }
}
