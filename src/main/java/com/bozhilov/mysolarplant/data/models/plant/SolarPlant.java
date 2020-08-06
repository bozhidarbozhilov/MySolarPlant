package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.data.models.other.Location;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="solar_plants")
public class SolarPlant extends BaseEntity {
    private Location location;
    private List<PVPanelsInstalled> panels;
    private List<Inverter> inverters;
    private List<ChargeController> chargeControllers;
    private BatteryInstalled installedBattery;
    private Integer orientation;
    private Integer inclination;

    @ManyToOne
    @JoinColumn(name="location_id", referencedColumnName = "id")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="plants_panels_installed",
            joinColumns = @JoinColumn(name="plant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="panels_installed_id", referencedColumnName = "id"))
    public List<PVPanelsInstalled> getPanels() {
        return panels;
    }

    public void setPanels(List<PVPanelsInstalled> panels) {
        this.panels = panels;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="plants_inverters",
            joinColumns = @JoinColumn(name="plant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="inverter_id", referencedColumnName = "id"))
    public List<Inverter> getInverters() {
        return inverters;
    }

    public void setInverters(List<Inverter> inverters) {
        this.inverters = inverters;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="plants_controllers",
            joinColumns = @JoinColumn(name="plant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="controller_id", referencedColumnName = "id"))
    public List<ChargeController> getChargeControllers() {
        return chargeControllers;
    }

    public void setChargeControllers(List<ChargeController> chargeControllers) {
        this.chargeControllers = chargeControllers;
    }

    @ManyToOne
    @JoinColumn(name="battery_installed_id", referencedColumnName = "id")
    public BatteryInstalled getInstalledBattery() {
        return installedBattery;
    }

    public void setInstalledBattery(BatteryInstalled installedBattery) {
        this.installedBattery = installedBattery;
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



}
