package com.bozhilov.mysolarplant.data.models.other;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Location extends BaseEntity {
    private String town;
    private String municipality;
    private String country;


    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
