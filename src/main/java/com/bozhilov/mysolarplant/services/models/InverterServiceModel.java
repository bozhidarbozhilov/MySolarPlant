package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

public class InverterServiceModel extends BaseParamsServiceModel{
    private Double ACPower;
    private Double maxACPower;
    private Double maxPVPower;

    @DecimalMin(value="0.01",message= Constants.INVERTER_AC_POWER_INVALID_VALUE)
    public Double getACPower() {
        return ACPower;
    }

    public void setACPower(Double ACPower) {
        this.ACPower = ACPower;
    }

    @DecimalMin(value="0.01",message= Constants.INVERTER_MAX_AC_POWER_INVALID_VALUE)
    public Double getMaxACPower() {
        return maxACPower;
    }

    public void setMaxACPower(Double maxACPower) {
        this.maxACPower = maxACPower;
    }

    @DecimalMin(value="0.01",message= Constants.INVERTER_MAX_PV_POWER_INVALID_VALUE)
    public Double getMaxPVPower() {
        return maxPVPower;
    }

    public void setMaxPVPower(Double maxPVPower) {
        this.maxPVPower = maxPVPower;
    }
}
