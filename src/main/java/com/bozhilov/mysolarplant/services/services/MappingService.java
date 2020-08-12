package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.web.models.SolarPlantCreateModel;
import com.bozhilov.mysolarplant.web.models.SolarUnitAllViewModel;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;

public interface MappingService {
    SolarUnitServiceModel solarUnitViewToService(SolarUnitCreateModel solarUnitCreateModel);
    SolarPlantServiceModel solarPlantCreateToService(SolarPlantCreateModel solarPlantCreateModel);

}
