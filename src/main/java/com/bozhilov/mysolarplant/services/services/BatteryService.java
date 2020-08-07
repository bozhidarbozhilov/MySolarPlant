package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;

import java.io.InvalidObjectException;

public interface BatteryService {
    BatteryServiceModel saveBattery(BatteryServiceModel batteryServiceModel) throws InvalidObjectException;
}
