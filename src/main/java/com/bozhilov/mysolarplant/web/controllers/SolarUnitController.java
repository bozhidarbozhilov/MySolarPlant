package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.*;
import com.bozhilov.mysolarplant.services.services.SolarUnitService;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.web.annotations.PageTitle;
import com.bozhilov.mysolarplant.web.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.InvalidObjectException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/solar_units")
public class SolarUnitController extends BaseController{
    private final SolarUnitService solarUnitService;
    private final UserService userService;
    private final ModelMapper mapper;
    private AllComponentsServiceModels allComponents;

    @Autowired
    public SolarUnitController(SolarUnitService solarUnitService,
                               UserService userService, ModelMapper mapper) {
        this.solarUnitService = solarUnitService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostConstruct
    private void postConstruct(){
        allComponents = solarUnitService.getAllComponentsForUnit();
    }

    @PageTitle("Solar Unit Create")
    @GetMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getSolarUnitCreatePage(@ModelAttribute(name="solarUnitCreate")
                                                       SolarUnitCreateModel solarUnitCreateModel,
                                               ModelAndView modelAndView){
        modelAndView.setViewName(super.view("solar-plants/solar-unit-create"));

        List<PVPanelViewModel> allPanels = allComponents.getAllPanels()
                .stream()
                .map(service->mapper.map(service, PVPanelViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("allPanels", allPanels);

        List<InverterViewModel> allInverters = allComponents.getAllInverters()
                .stream()
                .map(inverter->mapper.map(inverter, InverterViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("allInverters",allInverters);

        List<BatteryViewModel> allBatteries = allComponents.getAllBatteries()
                .stream()
                .map(battery->mapper.map(battery, BatteryViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("allBatteries", allBatteries);

        List<ChargeControllerViewModel> allControllers = allComponents.getAllControllers()
                .stream()
                .map(controller-> mapper.map(controller, ChargeControllerViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("allControllers", allControllers);


        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createSolarUnit(@ModelAttribute(name = "solarUnitCreate") SolarUnitCreateModel solarUnitCreateModel,
                                        BindingResult bindingResult, Principal principal,
                                        ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("solar-plants/solar-unit-create"));
        }else {
            SolarUnitServiceModel solarUnitServiceModel = convertCreateToServiceModel(solarUnitCreateModel, principal.getName());
            solarUnitService.createSolarUnit(solarUnitServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }

    @PageTitle("All Solar Units")
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

    private SolarUnitServiceModel convertCreateToServiceModel(SolarUnitCreateModel solarUnitCreateModel, String username){
        UserServiceModel user = userService.findByUsername(username);
        SolarUnitServiceModel solarUnitServiceModel = mapper.map(solarUnitCreateModel, SolarUnitServiceModel.class);
        solarUnitServiceModel.setUser(user);
        if(solarUnitCreateModel.getPanelId()!=null && !solarUnitCreateModel.getPanelId().isEmpty()){
            PVPanelServiceModel panelServiceModel = allComponents
                    .getAllPanels()
                    .stream()
                    .filter(panel->panel.getId().equals(solarUnitCreateModel.getPanelId()))
                    .findFirst().get();
            solarUnitServiceModel.setPanels(panelServiceModel);
        }
        if(solarUnitCreateModel.getBatteryId()!=null && !solarUnitCreateModel.getBatteryId().isEmpty()){
            BatteryServiceModel batteryServiceModel = allComponents
                    .getAllBatteries()
                    .stream()
                    .filter(battery->battery.getId().equals(solarUnitCreateModel.getBatteryId()))
                    .findFirst().get();
            solarUnitServiceModel.setBatteryType(batteryServiceModel);
        }
        if(solarUnitCreateModel.getChargeControllerId()!=null && !solarUnitCreateModel.getChargeControllerId().isEmpty()){
            ChargeControllerServiceModel controllerServiceModel = allComponents
                    .getAllControllers()
                    .stream()
                    .filter(controller->controller.getId().equals(solarUnitCreateModel.getChargeControllerId()))
                    .findFirst().get();
            solarUnitServiceModel.setChargeController(controllerServiceModel);
        }
        if(solarUnitCreateModel.getInverterId()!=null && !solarUnitCreateModel.getInverterId().isEmpty()){
            InverterServiceModel inverterServiceModel = allComponents
                    .getAllInverters()
                    .stream()
                    .filter(inverter->inverter.getId().equals(solarUnitCreateModel.getInverterId()))
                    .findFirst().get();
            solarUnitServiceModel.setInverter(inverterServiceModel);
        }

        return solarUnitServiceModel;
    }

}
