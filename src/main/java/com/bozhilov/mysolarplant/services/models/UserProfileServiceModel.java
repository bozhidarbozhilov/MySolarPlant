package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.services.services.SolarUnitService;

import java.util.ArrayList;
import java.util.List;

public class UserProfileServiceModel extends BaseServiceModel{
    private UserServiceModel user;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String information;


    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
