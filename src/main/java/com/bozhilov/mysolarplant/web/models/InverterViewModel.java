package com.bozhilov.mysolarplant.web.models;

public class InverterViewModel extends BaseParamsViewModel{
    private Double ACPower;
    private Double maxACPower;
    private Double maxPVPower;

    public Double getACPower() {
        return ACPower;
    }

    public void setACPower(Double ACPower) {
        this.ACPower = ACPower;
    }

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
