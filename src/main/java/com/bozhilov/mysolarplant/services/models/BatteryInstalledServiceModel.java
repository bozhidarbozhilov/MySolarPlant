package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.data.models.plant.Battery;
import com.bozhilov.mysolarplant.utils.Constants;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class BatteryInstalledServiceModel extends BaseServiceModel{
    private Battery battery;
    private Integer cellsCount;

    @NotEmpty(message= Constants.BATTERY_INSTALLED_NON_EMPTY_MESSAGE)
    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    @Min(value = 1, message=Constants.INVALID_BATTERY_CELLS_COUNT)
    public Integer getCellsCount() {
        return cellsCount;
    }

    public void setCellsCount(Integer cellsCount) {
        this.cellsCount = cellsCount;
    }
}
