package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.services.services.SolarUnitService;

import java.util.ArrayList;
import java.util.List;

public class UserProfileServiceModel {
    private UserServiceModel user;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String information;
    private List<SolarUnitServiceModel> solarUnits;
    private List<SolarPlantServiceModel> solarPlants;

    public UserProfileServiceModel(){
        solarUnits = new ArrayList<>();
        solarPlants = new ArrayList<>();
    }
}
