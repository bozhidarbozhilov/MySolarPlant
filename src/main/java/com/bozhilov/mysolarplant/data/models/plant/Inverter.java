package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="inverters")
@Getter
@Setter
public class Inverter extends BaseParams {
    @NotEmpty(message= Constants.INVERTER_AC_POWER_NOT_EMPTY)
    @Column(name="ac_power")
    private Double ACPower;
    @NotEmpty(message= Constants.INVERTER_MAX_AC_POWER_NOT_EMPTY)
    @Column(name="max_ac_power")
    private Double maxACPower;
    @NotEmpty(message= Constants.INVERTER_MAX_PV_POWER_NOT_EMPTY)
    @Column(name="max_pv_power")
    private Double maxPVPower;

}
