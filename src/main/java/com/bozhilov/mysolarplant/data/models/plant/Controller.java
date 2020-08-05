package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="controllers")
@Getter
@Setter
public class Controller extends BaseParams {
    @NotEmpty(message= Constants.CONTROLLER_VOLTAGE_NOT_EMPTY)
    private Double voltage;
    @NotEmpty(message= Constants.CONTROLLER_CURRENT_NOT_EMPTY)
    private Double current;
    @NotEmpty(message= Constants.CONTROLLER_POWER_NOT_EMPTY)
    private Double power;
}
