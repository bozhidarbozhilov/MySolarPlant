package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.InverterServiceModel;

import java.io.InvalidObjectException;

public interface InverterService {
    InverterServiceModel saveInverter(InverterServiceModel inverterServiceModel) throws InvalidObjectException;
}
