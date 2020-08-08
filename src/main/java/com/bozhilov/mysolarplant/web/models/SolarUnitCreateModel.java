package com.bozhilov.mysolarplant.web.models;

import com.bozhilov.mysolarplant.data.models.plant.Battery;
import com.bozhilov.mysolarplant.data.models.plant.ChargeController;
import com.bozhilov.mysolarplant.data.models.plant.Inverter;
import com.bozhilov.mysolarplant.data.models.plant.PVPanel;

public class SolarUnitCreateModel {
    private String panelId;
    private Integer panelsCount;
    private String inverterId;
    private String chargeControllerId;
    private String batteryId;
    private Integer batteryCellsCount;
    private Integer orientation;
    private Integer inclination;


    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public Integer getPanelsCount() {
        return panelsCount;
    }

    public void setPanelsCount(Integer panelsCount) {
        this.panelsCount = panelsCount;
    }

    public String getInverterId() {
        return inverterId;
    }

    public void setInverterId(String inverterId) {
        this.inverterId = inverterId;
    }

    public String getChargeControllerId() {
        return chargeControllerId;
    }

    public void setChargeControllerId(String chargeControllerId) {
        this.chargeControllerId = chargeControllerId;
    }

    public String getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(String batteryId) {
        this.batteryId = batteryId;
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
