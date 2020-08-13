package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;
import com.bozhilov.mysolarplant.services.services.BatteryService;
import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import com.bozhilov.mysolarplant.web.models.BatteryCreateModel;
import com.bozhilov.mysolarplant.web.models.BatteryEditModel;
import com.bozhilov.mysolarplant.web.models.BatteryViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.InvalidObjectException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/batteries")
public class BatteryController extends BaseController{

    private final BatteryService batteryService;
    private final ModelMapper mapper;

    public BatteryController(BatteryService batteryService, ModelMapper mapper) {
        this.batteryService = batteryService;
        this.mapper = mapper;
    }

    @PageTitle("Battery Create")
    @GetMapping("/create")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView getBatteryCreatePage(@ModelAttribute(name="batteryCreate")
                                                     BatteryCreateModel batteryCreateModel,
                                             ModelAndView modelAndView){
        modelAndView.setViewName(super.view("batteries/battery-create"));
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView createBattery(@ModelAttribute(name="batteryCreate") BatteryCreateModel batteryCreateModel,
                                      BindingResult bindingResult, ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("batteries/battery-create"));
        }else {
            BatteryServiceModel batteryServiceModel = mapper.map(batteryCreateModel, BatteryServiceModel.class);
            batteryService.saveBattery(batteryServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

    @GetMapping(value="/all", produces="application/json")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public Object getAllBatteries(){
        return batteryService
                .allBatteries()
                .stream()
                .map(batteryServiceModel -> mapper.map(batteryServiceModel, BatteryViewModel.class))
                .collect(Collectors.toUnmodifiableList());

    }
    @GetMapping(value="/all_solar_unit", produces="application/json")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public Object getAllBatteriesForSolarUnit(){
        return batteryService
                .allBatteries()
                .stream()
                .map(batteryServiceModel -> mapper.map(batteryServiceModel, BatteryViewModel.class))
                .collect(Collectors.toUnmodifiableList());

    }

    @PageTitle("Battery Edit")
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView getBatteryEditPage(@PathVariable("id") String id,
                              ModelAndView modelAndView){
        BatteryServiceModel  foundBattery = batteryService.findBatteryById(id);
        BatteryEditModel batteryForEdit = mapper.map(foundBattery, BatteryEditModel.class);
        modelAndView.addObject("batteryEditModel", batteryForEdit);
        modelAndView.setViewName(super.view("batteries/battery-edit"));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView editBattery(@ModelAttribute("batteryEditModel") BatteryEditModel batteryEditModel,
                                    BindingResult bindingResult, @PathVariable("id") String id,
                                    ModelAndView modelAndView) throws InvalidObjectException {

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("batteries/battery-edit");
        }else {
            BatteryServiceModel batteryServiceModel = mapper.map(batteryEditModel, BatteryServiceModel.class);
            BatteryServiceModel editedBattery = batteryService.editBattery(batteryServiceModel);
            modelAndView.addObject("batteryEditModel" ,
                    mapper.map(editedBattery, BatteryEditModel.class));
            modelAndView.setViewName(view("batteries/battery-edit"));
        }
        return modelAndView;
    }

    @PageTitle("Battery Delete")
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView getBatteryDeletePage(@PathVariable("id") String id,
                                        ModelAndView modelAndView){
       BatteryServiceModel batteryForEdit = batteryService.findBatteryById(id);
        BatteryViewModel batteryViewModel = mapper.map(batteryForEdit, BatteryViewModel.class);
        modelAndView.addObject("batteryDeleteModel", batteryViewModel);
        modelAndView.setViewName(super.view("batteries/battery-delete"));
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ENGINEER')")
    public ModelAndView deleteBattery(@PathVariable("id") String id,
                                  ModelAndView modelAndView){
        batteryService.deleteBattery(id);
        modelAndView.setViewName(super.redirect("home"));
        return modelAndView;
    }
}
