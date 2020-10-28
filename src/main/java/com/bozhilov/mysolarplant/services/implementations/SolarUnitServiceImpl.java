package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.SolarUnit;
import com.bozhilov.mysolarplant.data.repositories.SolarUnitRepository;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.services.services.*;
import com.bozhilov.mysolarplant.utils.Constants;
import com.bozhilov.mysolarplant.web.models.AllComponentsServiceModels;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolarUnitServiceImpl implements SolarUnitService {
    private final SolarUnitRepository solarUnitRepository;
    private final PVPanelService panelService;
    private final ChargeControllerService controllerService;
    private final InverterService inverterService;
    private final BatteryService batteryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public SolarUnitServiceImpl(SolarUnitRepository solarUnitRepository,
                                PVPanelService panelService, ChargeControllerService controllerService, InverterService inverterService, BatteryService batteryService, UserService userService, ModelMapper modelMapper,
                                Validator validator) {
        this.solarUnitRepository = solarUnitRepository;
        this.panelService = panelService;
        this.controllerService = controllerService;
        this.inverterService = inverterService;
        this.batteryService = batteryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public SolarUnitServiceModel createSolarUnit(SolarUnitServiceModel solarUnitServiceModel) throws InvalidObjectException {
        if(validator.validate(solarUnitServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_SOLAR_UNIT_PROPERTIES);
        }
        SolarUnit solarUnit = modelMapper.map(solarUnitServiceModel, SolarUnit.class);
        SolarUnit savedUnit = solarUnitRepository.save(solarUnit);

        return modelMapper.map(savedUnit, SolarUnitServiceModel.class);
    }

    @Override
    public List<SolarUnitServiceModel> findAllByUsername(String username) {
        List<SolarUnit> solarUnits = solarUnitRepository.findAllByUsername(username);

        return solarUnits
                .stream()
                .map(solarUnit -> modelMapper.map(solarUnit, SolarUnitServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public AllComponentsServiceModels getAllComponentsForUnit() {
        AllComponentsServiceModels allComponents= new AllComponentsServiceModels();
        allComponents.setAllPanels(panelService.allPanels());
        allComponents.setAllInverters(inverterService.allInverters());
        allComponents.setAllBatteries(batteryService.allBatteries());
        allComponents.setAllControllers(controllerService.allControllers());

        return allComponents;
    }

    @Override
    public SolarUnitServiceModel findById(String id) {
        SolarUnit solarUnit = solarUnitRepository
                .findById(id).orElseThrow(()->new IllegalArgumentException(Constants.SOLAR_UNIT_NOT_FOUND));

        return modelMapper.map(solarUnit, SolarUnitServiceModel.class);
    }


}
