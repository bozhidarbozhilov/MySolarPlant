package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.data.models.other.Location;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="solar_plants")
@Getter
@Setter
public class SolarPlant extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="location_id", referencedColumnName = "id")
    private Location location;
    @ManyToOne
    @JoinColumn(name="panel_id",referencedColumnName = "id")
    private PVPanel panels;
    private Integer panelsCount;
    @ManyToOne
    @JoinColumn(name="inverter_id", referencedColumnName = "id")
    private Inverter inverter;
    @ManyToOne
    @JoinColumn(name="controller_id", referencedColumnName = "id")
    private Controller controller;
    @ManyToOne
    @JoinColumn(name="battery_id", referencedColumnName = "id")
    private Battery battery;
    @Column(name="battery_cells_count")
    private Integer batteryCellsCount;
    @Column(name="total_battery_capacity")
    private Double totalBatteryCapacity;
    private Double installedPower;
    private Integer orientation;
    private Integer inclination;

}
