package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.Battery;
import com.bozhilov.mysolarplant.data.models.plant.ChargeController;
import com.bozhilov.mysolarplant.data.repositories.ChargeControllerRepository;
import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;
import com.bozhilov.mysolarplant.services.services.ChargeControllerService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargeControllerServiceImpl implements ChargeControllerService {
    private final ChargeControllerRepository controllerRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public ChargeControllerServiceImpl(ChargeControllerRepository controllerRepository, ModelMapper mapper, Validator validator) {
        this.controllerRepository = controllerRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public ChargeControllerServiceModel saveController(ChargeControllerServiceModel chargeControllerServiceModel) throws InvalidObjectException {
        if(validator.validate(chargeControllerServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_CONTROLLER_PROPERTIES);
        }
        ChargeController chargeController = mapper.map(chargeControllerServiceModel, ChargeController.class);
        ChargeController savedChargeController = controllerRepository.save(chargeController);
        return mapper.map(savedChargeController, ChargeControllerServiceModel.class);
    }

    @Override
    public List<ChargeControllerServiceModel> allControllers() {
        return controllerRepository
                .findAll()
                .stream()
                .map(controller-> mapper.map(controller, ChargeControllerServiceModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public ChargeControllerServiceModel findControllerById(String id) {
        ChargeController foundController = controllerRepository
                .findById(id)
                .orElseThrow(()->new IllegalArgumentException(Constants.CONTROLLER_NOT_FOUND));
        return mapper.map(foundController, ChargeControllerServiceModel.class);
    }

    @Override
    public ChargeControllerServiceModel editController(ChargeControllerServiceModel chargeControllerServiceModel) throws InvalidObjectException {
        if(validator.validate(chargeControllerServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_CONTROLLER_PROPERTIES);
        }
        ChargeController controllerForEdit = mapper.map(chargeControllerServiceModel, ChargeController.class);
        ChargeController editedController = controllerRepository.saveAndFlush(controllerForEdit);
        return mapper.map(editedController, ChargeControllerServiceModel.class);
    }

    @Override
    public void deleteController(String id) {
        ChargeController chargeController = controllerRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException(Constants.CONTROLLER_NOT_FOUND));
        controllerRepository.delete(chargeController);
    }
}
