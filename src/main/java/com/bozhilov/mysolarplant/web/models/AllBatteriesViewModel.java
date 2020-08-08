package com.bozhilov.mysolarplant.web.models;

public class AllBatteriesViewModel extends BaseParamsViewModel {
    private Double capacity;
    private Double voltage;

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }
}
