package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.web.models.SolarUnitCreateModel;

public interface MappingService {
    SolarUnitServiceModel solarUnitViewToService(SolarUnitCreateModel solarUnitCreateModel);
}
