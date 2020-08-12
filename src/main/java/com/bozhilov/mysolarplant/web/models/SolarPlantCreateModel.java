package com.bozhilov.mysolarplant.web.models;

import com.bozhilov.mysolarplant.services.models.UserServiceModel;

import java.util.List;

public class SolarPlantCreateModel extends BaseViewModel {
    private String town;
    private String municipality;
    private String country;
    private List<String> solarUnitsIds;
    private String username;


    public List<String> getSolarUnitsIds() {
        return solarUnitsIds;
    }

    public void setSolarUnitsIds(List<String> solarUnitsIds) {
        this.solarUnitsIds = solarUnitsIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
