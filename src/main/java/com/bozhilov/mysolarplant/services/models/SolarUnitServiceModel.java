package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SolarUnitServiceModel {
    private PVPanelServiceModel panels;
    private Integer panelsCount;
    private InverterServiceModel inverter;
    private ChargeControllerServiceModel chargeController;
    private BatteryServiceModel batteryType;
    private Integer batteryCellsCount;
    private Integer orientation;
    private Integer inclination;

    @NotNull(message = Constants.SOLAR_UNIT_PANEL_ERROR)
    public PVPanelServiceModel getPanels() {
        return panels;
    }

    public void setPanels(PVPanelServiceModel panels) {
        this.panels = panels;
    }

    @Min(value = 1, message=Constants.SOLAR_UNIT_PANEL_COUNT_ERROR)
    public Integer getPanelsCount() {
        return panelsCount;
    }

    public void setPanelsCount(Integer panelsCount) {
        this.panelsCount = panelsCount;
    }

    public InverterServiceModel getInverter() {
        return inverter;
    }

    public void setInverter(InverterServiceModel inverter) {
        this.inverter = inverter;
    }

    public ChargeControllerServiceModel getChargeController() {
        return chargeController;
    }

    public void setChargeController(ChargeControllerServiceModel chargeController) {
        this.chargeController = chargeController;
    }

    public BatteryServiceModel getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(BatteryServiceModel batteryType) {
        this.batteryType = batteryType;
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
