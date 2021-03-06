package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="inverters")
public class Inverter extends BaseParams {
    private Double ACPower;
    private Double maxACPower;
    private Double maxPVPower;

    @DecimalMin(value = "0.01", message= Constants.INVERTER_AC_POWER_INVALID_VALUE)
    @Column(name="ac_power")
    public Double getACPower() {
        return ACPower;
    }

    public void setACPower(Double ACPower) {
        this.ACPower = ACPower;
    }

    @DecimalMin(value="0.01", message= Constants.INVERTER_MAX_AC_POWER_INVALID_VALUE)
    @Column(name="max_ac_power")
    public Double getMaxACPower() {
        return maxACPower;
    }

    public void setMaxACPower(Double maxACPower) {
        this.maxACPower = maxACPower;
    }

    @DecimalMin(value="0.01", message= Constants.INVERTER_MAX_PV_POWER_INVALID_VALUE)
    @Column(name="max_pv_power")
    public Double getMaxPVPower() {
        return maxPVPower;
    }

    public void setMaxPVPower(Double maxPVPower) {
        this.maxPVPower = maxPVPower;
    }
}
