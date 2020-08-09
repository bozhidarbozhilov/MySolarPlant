package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;

import java.io.InvalidObjectException;

public interface SolarUnitService {
    SolarUnitServiceModel createSolarUnit(SolarUnitServiceModel solarUnitServiceModel) throws InvalidObjectException;
}
