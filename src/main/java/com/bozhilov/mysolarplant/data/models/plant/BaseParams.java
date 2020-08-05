package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
@Getter
@Setter
class BaseParams extends BaseEntity {
    @NotEmpty(message = Constants.MANUFACTURER_NOT_EMPTY)
    private String manufacturer;
    @NotEmpty(message = Constants.MODEL_NOT_EMPTY)
    private String model;
}
