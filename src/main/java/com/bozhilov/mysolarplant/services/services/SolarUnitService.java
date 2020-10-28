package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.web.models.AllComponentsServiceModels;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;

import java.io.InvalidObjectException;
import java.util.List;

public interface SolarUnitService {
    SolarUnitServiceModel createSolarUnit(SolarUnitServiceModel solarUnitServiceModel) throws InvalidObjectException;
    List<SolarUnitServiceModel> findAllByUsername(String username);
    AllComponentsServiceModels getAllComponentsForUnit();
    SolarUnitServiceModel findById(String id);
    SolarUnitServiceModel convertCreateToServiceModel(SolarUnitCreateModel solarUnitCreateModel, String username);
}
