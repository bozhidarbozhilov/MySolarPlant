package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
class BaseParams extends BaseEntity {
    private String manufacturer;
    private String model;

    @NotEmpty(message = Constants.MANUFACTURER_NOT_EMPTY)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @NotEmpty(message = Constants.MODEL_NOT_EMPTY)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
