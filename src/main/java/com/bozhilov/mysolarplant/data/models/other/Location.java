package com.bozhilov.mysolarplant.data.models.other;

import com.bozhilov.mysolarplant.data.models.BaseEntity;
import com.bozhilov.mysolarplant.utils.Constants;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="locations")
@Getter
@Setter
public class Location extends BaseEntity {
    private String town;
    private String municipality;
    private String country;
}
