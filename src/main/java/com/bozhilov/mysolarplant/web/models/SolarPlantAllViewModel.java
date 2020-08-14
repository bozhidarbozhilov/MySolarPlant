package com.bozhilov.mysolarplant.web.models;


public class SolarPlantAllViewModel extends BaseViewModel{
    private String town;
    private String municipality;
    private String country;
    private Integer solarUnitsCount;

    public SolarPlantAllViewModel() {
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

    public Integer getSolarUnitsCount() {
        return solarUnitsCount;
    }

    public void setSolarUnitsCount(Integer solarUnitsCount) {
        this.solarUnitsCount = solarUnitsCount;
    }
}
