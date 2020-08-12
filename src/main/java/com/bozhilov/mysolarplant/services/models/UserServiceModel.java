package com.bozhilov.mysolarplant.services.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static com.bozhilov.mysolarplant.utils.Constants.USERNAME_MAX_LENGTH;
import static com.bozhilov.mysolarplant.utils.Constants.USERNAME_MIN_LENGTH;

public class UserServiceModel extends BaseServiceModel{
    private String username;
    private String password;
    private UserProfileServiceModel profile;
    private List<SolarUnitServiceModel> solarUnits;
    private List<SolarPlantServiceModel> solarPlants;

    public UserServiceModel(){
        solarUnits = new ArrayList<>();
        solarPlants = new ArrayList<>();
    }

    @NotNull
    @Size(min=USERNAME_MIN_LENGTH,
            max = USERNAME_MAX_LENGTH)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfileServiceModel getProfile() {
        return profile;
    }

    public void setProfile(UserProfileServiceModel profile) {
        this.profile = profile;
    }

    public List<SolarUnitServiceModel> getSolarUnits() {
        return solarUnits;
    }

    public void setSolarUnits(List<SolarUnitServiceModel> solarUnits) {
        this.solarUnits = solarUnits;
    }

    public List<SolarPlantServiceModel> getSolarPlants() {
        return solarPlants;
    }

    public void setSolarPlants(List<SolarPlantServiceModel> solarPlants) {
        this.solarPlants = solarPlants;
    }
}
