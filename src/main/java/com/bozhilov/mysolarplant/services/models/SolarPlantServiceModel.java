package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.data.models.plant.*;

import javax.persistence.*;
import java.util.List;

public class SolarPlantServiceModel extends BaseServiceModel{
    private Location location;
    private List<SolarUnitServiceModel> solarUnits;

}
