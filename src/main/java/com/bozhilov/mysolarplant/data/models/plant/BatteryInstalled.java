package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="battery_installed")
public class BatteryInstalled extends BaseEntity {
    private Battery battery;
    private Integer cellsCount;

    @NotEmpty(message= Constants.BATTERY_INSTALLED_NON_EMPTY_MESSAGE)
    @ManyToOne
    @JoinColumn(name="battery_id", referencedColumnName = "id")
    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    @Column(name="cells_count")
    @Min(value = 1, message=Constants.INVALID_BATTERY_CELLS_COUNT)
    public Integer getCellsCount() {
        return cellsCount;
    }

    public void setCellsCount(Integer cellsCount) {
        this.cellsCount = cellsCount;
    }
}
