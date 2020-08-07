package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

public class PVPanelServiceModel extends BaseParamsServiceModel{
    private String connector;
    private Double power;
    private Double voltageAtMaxPower;
    private Double currentAtMaxPower;

    @DecimalMin(value = "0.01",message=Constants.INVALID_PANEL_POWER)
    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    @NotEmpty(message = Constants.CONNECTOR_NOT_EMPTY)
    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
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
