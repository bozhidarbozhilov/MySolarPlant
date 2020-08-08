package com.bozhilov.mysolarplant.web.models;

public class AllControllersViewModel extends BaseParamsViewModel {
    private Double voltage;
    private Double power;

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }
}
