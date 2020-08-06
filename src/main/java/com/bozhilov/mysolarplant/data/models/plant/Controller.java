package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="controllers")
public class Controller extends BaseParams {
    private Double voltage;
    private Double current;
    private Double power;

    @NotEmpty(message= Constants.CONTROLLER_VOLTAGE_NOT_EMPTY)
    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    @NotEmpty(message= Constants.CONTROLLER_CURRENT_NOT_EMPTY)
    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    @NotEmpty(message= Constants.CONTROLLER_POWER_NOT_EMPTY)
    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }
}
