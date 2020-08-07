package com.bozhilov.mysolarplant.web.controllers;

import com.bozhilov.mysolarplant.services.models.PVPanelServiceModel;
import com.bozhilov.mysolarplant.services.services.PVPanelService;
import com.bozhilov.mysolarplant.web.models.PVPanelCreateModel;
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
@RequestMapping("/pvpanels")
public class PVPanelController extends BaseController{
    private final PVPanelService pvPanelService;
    private final ModelMapper mapper;

    @Autowired
    public PVPanelController(PVPanelService pvPanelService, ModelMapper mapper) {
        this.pvPanelService = pvPanelService;
        this.mapper = mapper;
    }

    @GetMapping("/create")
    public ModelAndView getPVPanelCreatePage(@ModelAttribute(name="createPVPanel") PVPanelCreateModel pvPanelCreateModel,
                                             ModelAndView modelAndView){
        modelAndView.setViewName(super.view("pvpanels/pvpanel-create"));
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createPVPanel(@ModelAttribute(name="createPVPanel") PVPanelCreateModel pvPanelCreateModel,
                                      BindingResult bindingResult, ModelAndView modelAndView) throws InvalidObjectException {
        if(bindingResult.hasErrors()){
            modelAndView.setViewName(super.view("pvpanels/pvpanel-create"));
        }else {
            PVPanelServiceModel pvPanelServiceModel = mapper.map(pvPanelCreateModel, PVPanelServiceModel.class);
            pvPanelService.save(pvPanelServiceModel);
            modelAndView.setViewName(super.redirect("home"));
        }
        return modelAndView;
    }
}
