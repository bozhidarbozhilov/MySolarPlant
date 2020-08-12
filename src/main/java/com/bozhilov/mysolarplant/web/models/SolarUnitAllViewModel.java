package com.bozhilov.mysolarplant.web.models;

public class SolarUnitAllViewModel extends BaseViewModel{
    private String panelsModel;
    private Integer panelsCount;
    private String inverterModel;
    private String chargeControllerModel;
    private String batteryTypeModel;
    private Integer batteryCellsCount;
    private Integer orientation;
    private Integer inclination;

    public String getPanelsModel() {
        return panelsModel;
    }

    public void setPanelsModel(String panelsModel) {
        this.panelsModel = panelsModel;
    }

    public Integer getPanelsCount() {
        return panelsCount;
    }

    public void setPanelsCount(Integer panelsCount) {
        this.panelsCount = panelsCount;
    }

    public String getInverterModel() {
        return inverterModel;
    }

    public void setInverterModel(String inverterModel) {
        this.inverterModel = inverterModel;
    }

    public String getChargeControllerModel() {
        return chargeControllerModel;
    }

    public void setChargeControllerModel(String chargeControllerModel) {
        this.chargeControllerModel = chargeControllerModel;
    }

    public String getBatteryTypeModel() {
        return batteryTypeModel;
    }

    public void setBatteryTypeModel(String batteryTypeModel) {
        this.batteryTypeModel = batteryTypeModel;
    }

    public Integer getBatteryCellsCount() {
        return batteryCellsCount;
    }

    public void setBatteryCellsCount(Integer batteryCellsCount) {
        this.batteryCellsCount = batteryCellsCount;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Integer getInclination() {
        return inclination;
    }

    public void setInclination(Integer inclination) {
        this.inclination = inclination;
    }
}
