package com.bozhilov.mysolarplant.services.models;

import com.bozhilov.mysolarplant.utils.Constants;

import javax.validation.constraints.NotEmpty;

public class BaseParamsServiceModel extends BaseServiceModel{
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
