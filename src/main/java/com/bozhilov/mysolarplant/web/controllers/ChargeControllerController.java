package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;
import com.bozhilov.mysolarplant.services.services.ChargeControllerService;
import com.bozhilov.mysolarplant.web.models.*;
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
    public ModelAndView createController(@ModelAttribute(name="controllerCreate")
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

    @GetMapping(value = "/all_solar_unit", produces = "application/json")
    @ResponseBody
    public Object allControllersForSolarUnit(){
        return controllerService
                .allControllers()
                .stream()
                .map(controller->mapper.map(controller, ChargeControllerViewModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getControllerEditPage(@PathVariable("id") String id,
                                           ModelAndView modelAndView){
        ChargeControllerServiceModel  foundController = controllerService.findControllerById(id);
        ChargeControllerEditModel controllerForEdit = mapper.map(foundController, ChargeControllerEditModel.class);
        modelAndView.addObject("controllerEditModel", controllerForEdit);
        modelAndView.setViewName(super.view("controllers/controller-edit"));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editController(@ModelAttribute("controllerEditModel") ChargeControllerEditModel controllerEditModel,
                                    BindingResult bindingResult, @PathVariable("id") String id,
                                    ModelAndView modelAndView) throws InvalidObjectException {

        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("controllers/controller-edit"));
        }else {
            ChargeControllerServiceModel controllerServiceModel = mapper.map(controllerEditModel, ChargeControllerServiceModel.class);
            ChargeControllerServiceModel editedController = controllerService.editController(controllerServiceModel);
            modelAndView.addObject("controllerEditModel" ,
                    mapper.map(editedController, ChargeControllerEditModel.class));
            modelAndView.setViewName(super.view("controllers/controller-edit"));
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getControllerDeletePage(@PathVariable("id") String id,
                                        ModelAndView modelAndView){
        ChargeControllerServiceModel controllerForDelete = controllerService.findControllerById(id);
        ChargeControllerViewModel chargeControllerViewModel = mapper.map(controllerForDelete, ChargeControllerViewModel.class);
        modelAndView.addObject("controllerDeleteModel", chargeControllerViewModel);
        modelAndView.setViewName(super.view("controllers/controller-delete"));
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView deleteController(@PathVariable("id") String id,
                                  ModelAndView modelAndView){
        controllerService.deleteController(id);
        modelAndView.setViewName(super.redirect("home"));
        return modelAndView;
    }
}
