package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.SolarUnit;
import com.bozhilov.mysolarplant.data.repositories.SolarUnitRepository;
import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.services.services.BatteryService;
import com.bozhilov.mysolarplant.services.services.ChargeControllerService;
import com.bozhilov.mysolarplant.services.services.PVPanelService;
import com.bozhilov.mysolarplant.services.services.SolarUnitService;
import com.bozhilov.mysolarplant.utils.Constants;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;
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
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public SolarUnitServiceImpl(SolarUnitRepository solarUnitRepository,
                                ModelMapper modelMapper,
                                Validator validator) {
        this.solarUnitRepository = solarUnitRepository;
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
    public SolarUnitServiceModel findById(String id) {
        SolarUnit solarUnit = solarUnitRepository
                .findById(id).orElseThrow(()->new IllegalArgumentException(Constants.SOLAR_UNIT_NOT_FOUND));

        return modelMapper.map(solarUnit, SolarUnitServiceModel.class);
    }


}
