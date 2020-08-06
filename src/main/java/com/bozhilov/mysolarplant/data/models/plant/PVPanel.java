package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="pv_panels")
public class PVPanel extends BaseParams {

    private String connector;
    private Double power;

    private Double voltageAtMaxPower;

    private Double currentAtMaxPower;



    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    @NotEmpty(message = Constants.CONNECTOR_NOT_EMPTY)
    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    @Column(name="voltage_at_max_power")
    public Double getVoltageAtMaxPower() {
        return voltageAtMaxPower;
    }

    public void setVoltageAtMaxPower(Double voltageAtMaxPower) {
        this.voltageAtMaxPower = voltageAtMaxPower;
    }

    @Column(name="current_at_max_power")
    public Double getCurrentAtMaxPower() {
        return currentAtMaxPower;
    }

    public void setCurrentAtMaxPower(Double currentAtMaxPower) {
        this.currentAtMaxPower = currentAtMaxPower;
    }
}
