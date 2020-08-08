package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;

import java.io.InvalidObjectException;
import java.util.List;

public interface ChargeControllerService {
    ChargeControllerServiceModel saveController(ChargeControllerServiceModel chargeControllerServiceModel) throws InvalidObjectException;
    List<ChargeControllerServiceModel> allControllers();
}
