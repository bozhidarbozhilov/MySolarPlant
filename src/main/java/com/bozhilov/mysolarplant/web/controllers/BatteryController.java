package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
import com.bozhilov.mysolarplant.services.models.PVPanelServiceModel;
import com.bozhilov.mysolarplant.services.services.BatteryService;
import com.bozhilov.mysolarplant.web.models.AllBatteriesViewModel;
import com.bozhilov.mysolarplant.web.models.BatteryCreateModel;
import org.modelmapper.ModelMapper;
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

    @GetMapping("/create")
    public ModelAndView getBatteryCreatePage(@ModelAttribute(name="batteryCreate")
                                                     BatteryCreateModel batteryCreateModel,
                                             ModelAndView modelAndView){
        modelAndView.setViewName(super.view("batteries/battery-create"));
        return modelAndView;
    }

    @PostMapping("/create")
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
    public Object getAllBatteries(){
        return batteryService
                .allBatteries()
                .stream()
                .map(batteryServiceModel -> mapper.map(batteryServiceModel, AllBatteriesViewModel.class))
                .collect(Collectors.toUnmodifiableList());

    }
}
