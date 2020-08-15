package com.bozhilov.mysolarplant.web.models;

public class AllPanelsViewModel extends BaseParamsViewModel {
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

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }
}
