package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;

import java.io.InvalidObjectException;
import java.util.List;

public interface SolarUnitService {
    SolarUnitServiceModel createSolarUnit(SolarUnitServiceModel solarUnitServiceModel) throws InvalidObjectException;
    List<SolarUnitServiceModel> findAllByUsername(String username);
    SolarUnitServiceModel findById(String id);
}
