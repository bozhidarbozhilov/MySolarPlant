package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.Inverter;
import com.bozhilov.mysolarplant.data.repositories.InverterRepository;
import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;
import com.bozhilov.mysolarplant.services.models.InverterServiceModel;
import com.bozhilov.mysolarplant.services.services.InverterService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;

@Service
public class InverterServiceImpl implements InverterService {
    private final InverterRepository inverterRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public InverterServiceImpl(InverterRepository inverterRepository, ModelMapper modelMapper, Validator validator) {
        this.inverterRepository = inverterRepository;
        this.mapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public InverterServiceModel saveInverter(InverterServiceModel inverterServiceModel) throws InvalidObjectException {
        if(validator.validate(inverterServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_INVERTER_PROPERTIES);
        }
        Inverter inverter = mapper.map(inverterServiceModel, Inverter.class);
        Inverter savedInverter = inverterRepository.save(inverter);
        return mapper.map(savedInverter, InverterServiceModel.class);
    }
}
