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
    @NotEmpty(message = Constants.CONNECTOR_NOT_EMPTY)
    private String connector;
    private Double power;
    @Column(name="voltage_at_max_power")
    private Double voltageAtMaxPower;
    @Column(name="current_at_max_power")
    private Double currentAtMaxPower;
}
