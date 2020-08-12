package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;

import java.io.InvalidObjectException;
import java.util.List;

public interface BatteryService {
    BatteryServiceModel saveBattery(BatteryServiceModel batteryServiceModel) throws InvalidObjectException;
    List<BatteryServiceModel> allBatteries();
    BatteryServiceModel findBatteryById(String id);
    BatteryServiceModel editBattery(BatteryServiceModel batteryForEdit) throws InvalidObjectException;
    void deleteBattery(String id);
}
