package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="batteries_installed")
public class BatteriesInstalled extends BaseEntity {
    private Battery battery;
    private Integer cellsCount;

    @NotEmpty(message= Constants.BATTERY_INSTALLED_NON_EMPTY_MESSAGE)
    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public Integer getCellsCount() {
        return cellsCount;
    }

    public void setCellsCount(Integer cellsCount) {
        this.cellsCount = cellsCount;
    }
}
