package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="batteries")
@Getter
@Setter
public class Battery extends BaseParams {
    @NotEmpty(message = Constants.BATTERY_CAPACITY_NOT_EMPTY)
    private Double capacity;
    @NotEmpty(message = Constants.BATTERY_VOLTAGE_NOT_EMPTY)
    private Double voltage;
    private Double terminals;
    @Column(name="connection_type")
    private String conectionType;
}
