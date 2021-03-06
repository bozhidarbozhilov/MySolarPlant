package com.bozhilov.mysolarplant.utils;

import com.bozhilov.mysolarplant.data.models.other.ConnectionType;
import com.bozhilov.mysolarplant.data.models.other.Terminals;

public class Constants {
    //TODO Repair min values
    public static final int USERNAME_MIN_LENGTH = 5;
    public static final int USERNAME_MAX_LENGTH = 30;
    public static final int PASSWORD_MIN_LENGTH = 5;
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
    public static final String INVALID_USER_PROFILE_PROPERTIES = "Invalid user profile properties.";
    public static final String USER_PROFILE_NOT_FOUND = "User profile not found";

    public static final String ROLE_ENGINEER_NAME = "ROLE_ENGINEER";
    public static final String ROLE_USER_NAME = "ROLE_USER";
    public static final String ROLE_ADMIN_NAME = "ROLE_ADMIN";
    public static final String USER_NOT_FOUND = "User Not Found.";
    public static final String INVALID_LOCATION_PARAMS = "Invalid location parameters. ";
    public static final String SOLAR_UNIT_NOT_FOUND = "Solar unit not found.";
    public static final String USERNAME_ALREADY_EXIST_MESSAGE = "This username is already taken.";

    //TEST CONSTANTS
    public static final String TEST_BATTERY_ID = "a031a0c2-6847-4189-a72a-51ed76dcafb6";
    public static final String TEST_BATTERY_MANUFACTURER = "MONBAT MVR";
    public static final String TEST_BATTERY_MODEL = "12MVR65TA";
    public static final Double TEST_BATTERY_CAPACITY = 70d;
    public static final ConnectionType TEST_BATTERY_CONNECTION_TYPE = ConnectionType.valueOf("PARALLEL");
    public static final Terminals TEST_BATTERY_TERMINALS = Terminals.valueOf("FRONT");
    public static final Double TEST_BATTERY_VOLTAGE = 12d;

    public static final String TEST_CONTROLLER_ID = "b65f7d51-39aa-4f90-8c63-5d599e9736d9";
    public static final String TEST_CONTROLLER_MANUFACTURER = "Steca";
    public static final String TEST_CONTROLLER_MODEL = "StecaGrid 3000";
    public static final Double TEST_CONTROLLER_CURRENT= 10d;
    public static final Double TEST_CONTROLLER_POWER = 720d;
    public static final Double TEST_CONTROLLER_VOLTAGE = 12d;

    public static final String TEST_INVERTER_ID = "b65f7d51-39aa-4f90-8c63-5d599e9736d9";
    public static final String TEST_INVERTER_MANUFACTURER = "Steca";
    public static final String TEST_INVERTER_MODEL = "StecaGrid 3000";
    public static final Double TEST_INVERTER_AC_POWER = 3000d;
    public static final Double TEST_INVERTER_MAX_AC_POWER = 3000d;
    public static final Double TEST_INVERTER_MAX_PV_POWER = 3800d;

    public static final String TEST_PANEL_ID = "00e1d99d-29af-4f74-8603-6b378514eebj";
    public static final String TEST_PANEL_MANUFACTURER = "MITSUBISHI ELECTRIC";
    public static final String TEST_PANEL_MODEL = "PV-MLU255HC";
    public static final String TEST_PANEL_CONNECTOR = "MC";
    public static final Double TEST_PANEL_POWER = 225d;
    public static final Double TEST_PANEL_VOLTAGE_MAX_POWER = 31.2;
    public static final Double TEST_PANEL_CURRENT_MAX_POWER = 8.18;

    public static final String TEST_USER_USERNAME="test user";
    public static final String TEST_USER_PASSWORD="TEST PASSWORD";

}
