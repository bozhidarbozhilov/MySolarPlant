package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.services.models.*;
import com.bozhilov.mysolarplant.services.services.*;
import com.bozhilov.mysolarplant.web.models.SolarPlantCreateModel;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingServiceImpl implements MappingService {
    private final PVPanelService pvPanelService;
    private final ChargeControllerService controllerService;
    private final BatteryService batteryService;
    private final InverterService inverterService;
    private final UserService userService;
    private final SolarUnitService solarUnitService;
    private final ModelMapper modelMapper;

    public MappingServiceImpl(PVPanelService pvPanelService,
                              ChargeControllerService controllerService,
                              BatteryService batteryService,
                              InverterService inverterService,
                              UserService userService,
                              SolarUnitService solarUnitService,
                              ModelMapper modelMapper) {
        this.pvPanelService = pvPanelService;
        this.controllerService = controllerService;
        this.batteryService = batteryService;
        this.inverterService = inverterService;
        this.userService = userService;
        this.solarUnitService = solarUnitService;
        this.modelMapper = modelMapper;
    }


    @Override
    public SolarUnitServiceModel solarUnitViewToService(SolarUnitCreateModel solarUnitCreateModel) {
        UserServiceModel user = userService.findByUsername(solarUnitCreateModel.getUsername());
        SolarUnitServiceModel solarUnitServiceModel = modelMapper.map(solarUnitCreateModel, SolarUnitServiceModel.class);
        solarUnitServiceModel.setUser(user);
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

    @Override
    public SolarPlantServiceModel solarPlantCreateToService(SolarPlantCreateModel solarPlantCreateModel) {
        SolarPlantServiceModel solarPlantServiceModel = new SolarPlantServiceModel();
        Location location = new Location();
        location.setTown(solarPlantCreateModel.getTown());
        location.setMunicipality(solarPlantCreateModel.getMunicipality());
        location.setCountry(solarPlantCreateModel.getCountry());
        solarPlantServiceModel.setLocation(location);

        List<SolarUnitServiceModel> solarUnitServiceModelList =
                solarPlantCreateModel.getSolarUnitsIds()
                        .stream()
                        .map(solarUnitService::findById)
                        .collect(Collectors.toList());
        solarPlantServiceModel.setSolarUnits(solarUnitServiceModelList);

        UserServiceModel userServiceModel = userService.findByUsername(solarPlantCreateModel.getUsername());
        solarPlantServiceModel.setUser(userServiceModel);

        return solarPlantServiceModel;
    }

}
