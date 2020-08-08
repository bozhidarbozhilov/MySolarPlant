package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.InverterServiceModel;

import java.io.InvalidObjectException;
import java.util.List;

public interface InverterService {
    InverterServiceModel saveInverter(InverterServiceModel inverterServiceModel) throws InvalidObjectException;
    List<InverterServiceModel> allInverters();
    InverterServiceModel findInverterById(String id);
}
