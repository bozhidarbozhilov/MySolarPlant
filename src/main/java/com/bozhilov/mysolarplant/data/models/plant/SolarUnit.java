package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.data.models.users.User;
import com.bozhilov.mysolarplant.data.models.users.UserProfile;
import com.bozhilov.mysolarplant.utils.Constants;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="solar_units")
public class SolarUnit extends BaseEntity {
    private PVPanel panels;
    private Integer panelsCount;
    private Inverter inverter;
    private ChargeController chargeController;
    private Battery batteryType;
    private Integer batteryCellsCount;
    private Integer orientation;
    private Integer inclination;
    private User user;
    private List<SolarPlant> solarPlants;

    @ManyToOne
    @JoinColumn(name="panel_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = Constants.SOLAR_UNIT_PANEL_ERROR)
    public PVPanel getPanels() {
        return panels;
    }

    public void setPanels(PVPanel panels) {
        this.panels = panels;
    }

    @Min(value = 1, message=Constants.SOLAR_UNIT_PANEL_COUNT_ERROR)
    public Integer getPanelsCount() {
        return panelsCount;
    }

    public void setPanelsCount(Integer panelsCount) {
        this.panelsCount = panelsCount;
    }

    @ManyToOne
    @JoinColumn(name="inverter_id", referencedColumnName = "id")
    public Inverter getInverter() {
        return inverter;
    }

    public void setInverter(Inverter inverter) {
        this.inverter = inverter;
    }

    @ManyToOne
    @JoinColumn(name="controller_id", referencedColumnName = "id")
    public ChargeController getChargeController() {
        return chargeController;
    }

    public void setChargeController(ChargeController chargeController) {
        this.chargeController = chargeController;
    }

    @ManyToOne
    @JoinColumn(name="battery_id", referencedColumnName = "id")
    public Battery getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(Battery batteryType) {
        this.batteryType = batteryType;
    }

    @Column(name="battery_cells_count")
    public Integer getBatteryCellsCount() {
        return batteryCellsCount;
    }

    public void setBatteryCellsCount(Integer batteryCellsCount) {
        this.batteryCellsCount = batteryCellsCount;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Integer getInclination() {
        return inclination;
    }

    public void setInclination(Integer inclination) {
        this.inclination = inclination;
    }

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToMany(mappedBy = "solarUnits", targetEntity = SolarPlant.class)
    public List<SolarPlant> getSolarPlants() {
        return solarPlants;
    }

    public void setSolarPlants(List<SolarPlant> solarPlants) {
        this.solarPlants = solarPlants;
    }
}
