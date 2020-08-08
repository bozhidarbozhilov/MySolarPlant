package com.bozhilov.mysolarplant.web.models;

import com.bozhilov.mysolarplant.data.models.plant.Battery;
import com.bozhilov.mysolarplant.data.models.plant.ChargeController;
import com.bozhilov.mysolarplant.data.models.plant.Inverter;
import com.bozhilov.mysolarplant.data.models.plant.PVPanel;

public class SolarUnitCreateModel {
    private PVPanelViewModel panels;
    private Integer panelsCount;
    private InverterViewModel inverter;
    private ChargeControllerViewModel chargeController;
    private BatteryViewModel batteryType;
    private Integer batteryCellsCount;
    private Integer orientation;
    private Integer inclination;


    public PVPanelViewModel getPanels() {
        return panels;
    }

    public void setPanels(PVPanelViewModel panels) {
        this.panels = panels;
    }

    public Integer getPanelsCount() {
        return panelsCount;
    }

    public void setPanelsCount(Integer panelsCount) {
        this.panelsCount = panelsCount;
    }

    public InverterViewModel getInverter() {
        return inverter;
    }

    public void setInverter(InverterViewModel inverter) {
        this.inverter = inverter;
    }

    public ChargeControllerViewModel getChargeController() {
        return chargeController;
    }

    public void setChargeController(ChargeControllerViewModel chargeController) {
        this.chargeController = chargeController;
    }

    public BatteryViewModel getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(BatteryViewModel batteryType) {
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
