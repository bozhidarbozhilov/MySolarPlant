package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.data.models.plant.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

public class SolarPlantServiceModel extends BaseServiceModel{
    private Location location;
    private List<SolarUnitServiceModel> solarUnits;
    private UserServiceModel user;

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

    @JsonBackReference
    public UserServiceModel getUse() {
        return user;
    }

    public void setUser(UserServiceModel userServiceModel) {
        this.user = userServiceModel;
    }
}
