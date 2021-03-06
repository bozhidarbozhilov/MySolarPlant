package com.bozhilov.mysolarplant.services.services;

import com.bozhilov.mysolarplant.services.models.PVPanelServiceModel;

import java.io.InvalidObjectException;
import java.util.List;

public interface PVPanelService {
    PVPanelServiceModel save(PVPanelServiceModel pvPanelServiceModel) throws InvalidObjectException;
    List<PVPanelServiceModel> allPanels();
    PVPanelServiceModel findPanelById(String id);
    PVPanelServiceModel editPanel(PVPanelServiceModel pvPanelForEdit) throws InvalidObjectException;
    void deletePanel(String id);
}
