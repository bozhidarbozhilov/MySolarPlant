package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="controllers")
public class ChargeController extends BaseParams {
    private Double voltage;
    private Double current;
    private Double power;

    @DecimalMin(value="0.01", message = Constants.CONTROLLER_ZERO_VOLTAGE_ERROR_MESSAGE)
    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    @DecimalMin(value="0.01", message = Constants.CONTROLLER_ZERO_CURRENT_ERROR_MESSAGE)
    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    @DecimalMin(value="0.01", message = Constants.CONTROLLER_ZERO_POWER_ERROR_MESSAGE)
    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }
}
