package com.bozhilov.mysolarplant.web.models;

public class ChargeControllerViewModel extends BaseParamsViewModel{
    private Double voltage;
    private Double current;
    private Double power;

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }
}
