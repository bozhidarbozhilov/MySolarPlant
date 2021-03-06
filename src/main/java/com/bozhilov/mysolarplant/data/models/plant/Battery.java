package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.other.ConnectionType;
import com.bozhilov.mysolarplant.data.models.other.Terminals;
import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="batteries")
public class Battery extends BaseParams {
    private Double capacity;
    private Double voltage;
    private Terminals terminals;
    private ConnectionType connectionType;

    @DecimalMin(value="0.01", message = Constants.BATTERY_CAPACITY_INVALID_VALUE)
    @Basic
    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    @DecimalMin(value="0.01", message = Constants.BATTERY_VOLTAGE_INVALID_VALUE)
    @Basic
    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    @NotNull(message=Constants.BATTERY_TERMINALS_TYPE_NOT_EMPTY)
    @Column(name="terminals", nullable = false)
    @Enumerated(EnumType.STRING)
    public Terminals getTerminals() {
        return terminals;
    }

    public void setTerminals(Terminals terminals) {
        this.terminals = terminals;
    }


    @NotNull(message=Constants.BATTERY_CONNECTION_TYPE_NOT_EMPTY)
    @Column(name="connection_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }
}
