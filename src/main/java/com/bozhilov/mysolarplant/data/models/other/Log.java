package com.bozhilov.mysolarplant.data.models.other;

import com.bozhilov.mysolarplant.data.models.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="log")
public class Log extends BaseEntity {
    private String logString;

    public String getLogString() {
        return logString;
    }

    public void setLogString(String logString) {
        this.logString = logString;
    }
}
