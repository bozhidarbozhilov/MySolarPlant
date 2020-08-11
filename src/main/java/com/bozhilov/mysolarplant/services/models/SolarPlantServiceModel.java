package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.data.models.plant.*;

import javax.persistence.*;
import java.util.List;

public class SolarPlantServiceModel extends BaseServiceModel{
    private Location location;
    private List<SolarUnitServiceModel> solarUnits;
    private UserServiceModel userServiceModel;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<SolarUnitServiceModel> getSolarUnits() {
        return solarUnits;
    }

    public void setSolarUnits(List<SolarUnitServiceModel> solarUnits) {
        this.solarUnits = solarUnits;
    }

    public UserServiceModel getUserServiceModel() {
        return userServiceModel;
    }

    public void setUserServiceModel(UserServiceModel userServiceModel) {
        this.userServiceModel = userServiceModel;
    }
}
