package com.bozhilov.mysolarplant.web.models;

import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;
import com.bozhilov.mysolarplant.services.models.InverterServiceModel;
import com.bozhilov.mysolarplant.services.models.PVPanelServiceModel;

import java.util.List;

public class AllComponentsServiceModels {
    private List<PVPanelServiceModel> allPanels;
    private List<ChargeControllerServiceModel> allControllers;
    private List<BatteryServiceModel> allBatteries;
    private List<InverterServiceModel> allInverters;

    public List<PVPanelServiceModel> getAllPanels() {
        return allPanels;
    }

    public void setAllPanels(List<PVPanelServiceModel> allPanels) {
        this.allPanels = allPanels;
    }

    public List<ChargeControllerServiceModel> getAllControllers() {
        return allControllers;
    }

    public void setAllControllers(List<ChargeControllerServiceModel> allControllers) {
        this.allControllers = allControllers;
    }

    public List<BatteryServiceModel> getAllBatteries() {
        return allBatteries;
    }

    public void setAllBatteries(List<BatteryServiceModel> allBatteries) {
        this.allBatteries = allBatteries;
    }

    public List<InverterServiceModel> getAllInverters() {
        return allInverters;
    }

    public void setAllInverters(List<InverterServiceModel> allInverters) {
        this.allInverters = allInverters;
    }
}
