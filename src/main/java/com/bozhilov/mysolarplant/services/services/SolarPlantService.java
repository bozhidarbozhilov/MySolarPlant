package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.web.models.SolarPlantCreateModel;

import java.util.List;

public interface SolarPlantService {
    SolarPlantServiceModel createSolarPlant(SolarPlantServiceModel solarPlantServiceModel);
    List<SolarPlantServiceModel> findAllByUsername(String username);
    SolarPlantServiceModel findById(String id);
    List<SolarPlantServiceModel> findAll();
    SolarPlantServiceModel convertCreateToServiceModel(SolarPlantCreateModel solarPlantCreateModel);
}
