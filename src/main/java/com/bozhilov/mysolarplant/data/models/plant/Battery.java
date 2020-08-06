package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="batteries")
public class Battery extends BaseParams {
    private Double capacity;
    private Double voltage;
    private Double terminals;
    private String connectionType;

    @NotEmpty(message = Constants.BATTERY_CAPACITY_NOT_EMPTY)
    @Basic
    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    @NotEmpty(message = Constants.BATTERY_VOLTAGE_NOT_EMPTY)
    @Basic
    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    @Basic
    public Double getTerminals() {
        return terminals;
    }

    public void setTerminals(Double terminals) {
        this.terminals = terminals;
    }

    @Column(name="connection_type")
    @Basic
    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }
}
