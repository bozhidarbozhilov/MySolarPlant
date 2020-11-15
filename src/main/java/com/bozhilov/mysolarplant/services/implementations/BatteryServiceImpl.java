package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.Battery;
import com.bozhilov.mysolarplant.data.models.plant.PVPanel;
import com.bozhilov.mysolarplant.data.repositories.BatteryRepository;
import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
import com.bozhilov.mysolarplant.services.models.PVPanelServiceModel;
import com.bozhilov.mysolarplant.services.services.BatteryService;
import com.bozhilov.mysolarplant.utils.Constants;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {
    private final BatteryRepository batteryRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public BatteryServiceImpl(BatteryRepository batteryRepository, ModelMapper mapper, Validator validator) {
        this.batteryRepository = batteryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public BatteryServiceModel saveBattery(BatteryServiceModel batteryServiceModel) throws InvalidObjectException {
        if(validator.validate(batteryServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_BATTERY_PROPERTIES);
        }
        Battery battery = mapper.map(batteryServiceModel, Battery.class);
        Battery savedBattery = batteryRepository.save(battery);
        return mapper.map(savedBattery, BatteryServiceModel.class);
    }

    @Override
    public List<BatteryServiceModel> allBatteries() {
        return batteryRepository
                .findAll()
                .stream()
                .map(battery -> mapper.map(battery, BatteryServiceModel.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public BatteryServiceModel findBatteryById(String id) {
        Battery foundBattery = batteryRepository
                .findById(id)
                .orElseThrow(()-> new IllegalArgumentException(Constants.BATTERY_NOT_FOUND));
        return mapper.map(foundBattery, BatteryServiceModel.class);
    }

    @Override
    public BatteryServiceModel editBattery(BatteryServiceModel batteryForEdit) throws InvalidObjectException {
        if(validator.validate(batteryForEdit).size()>0){
            throw new InvalidObjectException(Constants.INVALID_BATTERY_PROPERTIES);
        }
        Battery battery = mapper.map(batteryForEdit, Battery.class);
        Battery editedBattery = batteryRepository.saveAndFlush(battery);
        return mapper.map(editedBattery, BatteryServiceModel.class);
    }

    @Override
    public void deleteBattery(String id) {
        Battery batteryForDelete = batteryRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(Constants.BATTERY_NOT_FOUND));
        batteryRepository.delete(batteryForDelete);
    }


}
