package com.bozhilov.mysolarplant.web.models;

import com.bozhilov.mysolarplant.data.models.other.ConnectionType;
import com.bozhilov.mysolarplant.data.models.other.Terminals;

public class BatteryViewModel extends BaseParamsViewModel{
    private Double capacity;
    private Double voltage;
    private Terminals terminals;
    private ConnectionType connectionType;

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

    public Terminals getTerminals() {
        return terminals;
    }

    public void setTerminals(Terminals terminals) {
        this.terminals = terminals;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }
}
