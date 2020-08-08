package com.bozhilov.mysolarplant.web.models;

public class AllInvertersViewModel extends BaseParamsViewModel {
    private Double maxACPower;
    private Double maxPVPower;

    public Double getMaxACPower() {
        return maxACPower;
    }

    public void setMaxACPower(Double maxACPower) {
        this.maxACPower = maxACPower;
    }

    public Double getMaxPVPower() {
        return maxPVPower;
    }

    public void setMaxPVPower(Double maxPVPower) {
        this.maxPVPower = maxPVPower;
    }
}
