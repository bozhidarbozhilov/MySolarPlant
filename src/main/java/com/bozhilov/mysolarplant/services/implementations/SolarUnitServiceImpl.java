package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.repositories.SolarUnitRepository;
import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.services.services.SolarUnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

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
    public SolarUnitServiceModel createSolarUnit(SolarPlantServiceModel solarPlantServiceModel) {
        return null;
    }
}
