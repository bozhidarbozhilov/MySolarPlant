package com.bozhilov.mysolarplant.utils;

public class Constants {
    //TODO Repair min values
    public static final int USERNAME_MIN_LENGTH = 1;
    public static final int USERNAME_MAX_LENGTH = 30;
    public static final int PASSWORD_MIN_LENGTH = 1;
    public static final int PASSWORD_MAX_LENGTH = 25;
    public static final String EMAIL_ALREADY_EXIST_MESSAGE = "Email already exist.";
    public static final String INVALID_USERS_PROPERTIES = "Invalid users properties.";

    public static final String MANUFACTURER_NOT_EMPTY ="Manufacturer can not be empty.";

    public static final String MODEL_NOT_EMPTY ="Model can not be empty.";

    public static final String INVALID_PANEL_POWER = "PV Panel must have power.";
    public static final String PANELS_NON_EMPTY_MESSAGE = "Panel must be chosen.";
    public static final String INVALID_PANELS_COUNT = "Panels can not be lower than 1.";
    public static final String INVALID_PV_PANEL_PROPERTIES = "PV Panel invalid properties.";
    public static final String PV_PANEL_NOT_FOUND = "PV Panel not found";

    public static final String CONNECTOR_NOT_EMPTY = "Panel can not be empty.";

    public static final String CONTROLLER_ZERO_VOLTAGE_ERROR_MESSAGE = "Controller must have voltage.";
    public static final String CONTROLLER_ZERO_CURRENT_ERROR_MESSAGE = "Controller must have current.";
    public static final String CONTROLLER_ZERO_POWER_ERROR_MESSAGE = "Controller must have power.";
    public static final String INVALID_CONTROLLER_PROPERTIES = "Controller invalid properties";
    public static final String CONTROLLER_NOT_FOUND = "Controller not found";

    public static final String INVERTER_AC_POWER_INVALID_VALUE = "Inverter ac power invalid value(must be> 0.01).";
    public static final String INVERTER_MAX_AC_POWER_INVALID_VALUE = "Inverter max ac power invalid value(must be> 0.01).";
    public static final String INVERTER_MAX_PV_POWER_INVALID_VALUE = "Inverter max PV power invalid value(must be> 0.01).";
    public static final String INVALID_INVERTER_PROPERTIES = "Inverter invalid properties";
    public static final String INVERTER_NOT_FOUND = "Inverter not found";

    public static final String BATTERY_CAPACITY_INVALID_VALUE = "Battery capacity invalid value(must be> 0.01).";
    public static final String BATTERY_VOLTAGE_INVALID_VALUE = "Battery voltage invalid value(must be> 0.01).";
    public static final String BATTERY_CONNECTION_TYPE_NOT_EMPTY = "Battery connection type cannot be empty";
    public static final String BATTERY_TERMINALS_TYPE_NOT_EMPTY = "Battery terminals type cannot be empty";
    public static final String BATTERY_NOT_FOUND = "Battery not found";
    public static final String BATTERY_INSTALLED_NON_EMPTY_MESSAGE = "Installed battery cannot be empty";
    public static final String INVALID_BATTERY_CELLS_COUNT = "Battery cells can not be lower than 1.";
    public static final String INVALID_BATTERY_PROPERTIES = "Battery invalid properties";

    public static final String SOLAR_PLANT_LOCATION_ERROR = "Solar plant must have location.";
    public static final String SOLAR_UNIT_PANEL_ERROR = "Solar unit must have solar panel";
    public static final String SOLAR_UNIT_PANEL_COUNT_ERROR = "Solar unit must have at least one solar panel";
    public static final String SOLAR_UNIT_INVERTER_ERROR = "Solar unit must have inverter";
    public static final String SOLAR_PLANT_UNIT_ERROR = "Solar plant must have at least one unit";

    public static final String INVALID_SOLAR_UNIT_PROPERTIES = "Invalid solar unit properties.";
}
