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

    public static final String PANELS_NON_EMPTY_MESSAGE = "Panel must be chosen.";
    public static final String INVALID_PANELS_COUNT = "Panels can not be lower than 1.";

    public static final String CONNECTOR_NOT_EMPTY = "Panel can not be empty.";

    public static final String CONTROLLER_VOLTAGE_NOT_EMPTY = "ChargeController must have voltage parameter.";
    public static final String CONTROLLER_CURRENT_NOT_EMPTY = "ChargeController must have current parameter.";
    public static final String CONTROLLER_POWER_NOT_EMPTY = "ChargeController must have power parameter.";

    public static final String INVERTER_AC_POWER_NOT_EMPTY = "Inverter must have ac power parameter.";
    public static final String INVERTER_MAX_AC_POWER_NOT_EMPTY = "Inverter must have max ac power parameter.";
    public static final String INVERTER_MAX_PV_POWER_NOT_EMPTY = "Inverter must have max PV power parameter.";

    public static final String BATTERY_VOLTAGE_NOT_EMPTY = "Battery must have voltage parameter.";
    public static final String BATTERY_CAPACITY_NOT_EMPTY = "Battery must have capacity parameter.";
    public static final String BATTERY_INSTALLED_NON_EMPTY_MESSAGE = "Installed battery can not be empty";
    public static final String INVALID_BATTERY_CELLS_COUNT = "Battery cells can not be lower than 1.";
}
