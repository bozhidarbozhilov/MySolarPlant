package com.bozhilov.mysolarplant.web.models;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.validation.constraints.DecimalMin;

public class PVPanelCreateModel extends BaseParamsViewModel{
    private String connector;
    private Double power;
    private Double voltageAtMaxPower;
    private Double currentAtMaxPower;

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    @DecimalMin(value = "0.01",message= Constants.INVALID_PANEL_POWER)
    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getVoltageAtMaxPower() {
        return voltageAtMaxPower;
    }

    public void setVoltageAtMaxPower(Double voltageAtMaxPower) {
        this.voltageAtMaxPower = voltageAtMaxPower;
    }

    public Double getCurrentAtMaxPower() {
        return currentAtMaxPower;
    }

    public void setCurrentAtMaxPower(Double currentAtMaxPower) {
        this.currentAtMaxPower = currentAtMaxPower;
    }
}
