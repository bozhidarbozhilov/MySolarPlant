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

}
