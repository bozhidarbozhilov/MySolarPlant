package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.services.models.*;
import com.bozhilov.mysolarplant.services.services.*;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MappingServiceImpl implements MappingService {
    private final PVPanelService pvPanelService;
    private final ChargeControllerService controllerService;
    private final BatteryService batteryService;
    private final InverterService inverterService;
    private final ModelMapper modelMapper;

    public MappingServiceImpl(PVPanelService pvPanelService,
                              ChargeControllerService controllerService,
                              BatteryService batteryService,
                              InverterService inverterService,
                              ModelMapper modelMapper) {
        this.pvPanelService = pvPanelService;
        this.controllerService = controllerService;
        this.batteryService = batteryService;
        this.inverterService = inverterService;
        this.modelMapper = modelMapper;
    }


    @Override
    public SolarUnitServiceModel solarUnitViewToService(SolarUnitCreateModel solarUnitCreateModel) {
        SolarUnitServiceModel solarUnitServiceModel = modelMapper.map(solarUnitCreateModel, SolarUnitServiceModel.class);

        if(solarUnitCreateModel.getPanelId()!=null && !solarUnitCreateModel.getPanelId().isEmpty()){
            PVPanelServiceModel panelServiceModel = pvPanelService.findPanelById(solarUnitCreateModel.getPanelId());
            solarUnitServiceModel.setPanels(panelServiceModel);
        }
        if(solarUnitCreateModel.getBatteryId()!=null && !solarUnitCreateModel.getBatteryId().isEmpty()){
            BatteryServiceModel batteryServiceModel = batteryService.findBatteryById(solarUnitCreateModel.getBatteryId());
            solarUnitServiceModel.setBatteryType(batteryServiceModel);
        }
        if(solarUnitCreateModel.getChargeControllerId()!=null && !solarUnitCreateModel.getChargeControllerId().isEmpty()){
            ChargeControllerServiceModel controllerServiceModel = controllerService.findControllerById(solarUnitCreateModel.getChargeControllerId());
            solarUnitServiceModel.setChargeController(controllerServiceModel);
        }
        if(solarUnitCreateModel.getInverterId()!=null && !solarUnitCreateModel.getInverterId().isEmpty()){
            InverterServiceModel inverterServiceModel = inverterService.findInverterById(solarUnitCreateModel.getInverterId());
            solarUnitServiceModel.setInverter(inverterServiceModel);
        }

        return solarUnitServiceModel;
    }
}
