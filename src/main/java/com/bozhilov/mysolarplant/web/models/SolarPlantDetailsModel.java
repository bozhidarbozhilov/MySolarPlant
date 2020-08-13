package com.bozhilov.mysolarplant.web.models;

import java.util.List;

public class SolarPlantDetailsModel extends BaseViewModel {
    private String town;
    private String municipality;
    private String country;
    private List<SolarUnitAllViewModel> solarUnits;

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

    public List<SolarUnitAllViewModel> getSolarUnits() {
        return solarUnits;
    }

    public void setSolarUnits(List<SolarUnitAllViewModel> solarUnits) {
        this.solarUnits = solarUnits;
    }
}
