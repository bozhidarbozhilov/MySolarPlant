package com.bozhilov.mysolarplant.data.models.plant;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.data.models.users.User;
import com.bozhilov.mysolarplant.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="solar_plants")
public class SolarPlant extends BaseEntity {
    private Location location;
    private List<SolarUnit> solarUnits;
    private User user;

    @ManyToOne
    @JoinColumn(name="location_id", referencedColumnName = "id", nullable = false)
    @NotNull(message= Constants.SOLAR_PLANT_LOCATION_ERROR)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="solar_plants_units",
            joinColumns = @JoinColumn(name="plant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="unit_id", referencedColumnName = "id"))
    @NotEmpty(message =Constants.SOLAR_PLANT_UNIT_ERROR)
    public List<SolarUnit> getSolarUnits() {
        return solarUnits;
    }

    public void setSolarUnits(List<SolarUnit> solarUnits) {
        this.solarUnits = solarUnits;
    }

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
