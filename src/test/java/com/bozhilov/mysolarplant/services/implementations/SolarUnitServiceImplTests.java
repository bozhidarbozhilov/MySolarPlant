package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.*;
import com.bozhilov.mysolarplant.data.models.users.User;
import com.bozhilov.mysolarplant.data.repositories.SolarUnitRepository;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SolarUnitServiceImplTests {
    @Spy
    private ModelMapper mockMapper;
    @Spy
    private Validator mockValidator;
    @Mock
    private BatteryServiceImpl batteryService;
    @Mock
    private ChargeControllerServiceImpl controllerService;
    @Mock
    private InverterServiceImpl inverterService;
    @Mock
    private PVPanelServiceImpl panelService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private SolarUnitRepository solarUnitRepository;
    @InjectMocks
    private SolarUnitServiceImpl solarUnitService;

    private Battery testBattery;
    private ChargeController testController;
    private Inverter testInverter;
    private PVPanel testPanel;
    private SolarUnit testSolarUnit;
    private User testUser;


    @BeforeEach
    public void setUp(){
        this.testBattery = new Battery();
        this.testBattery.setId(Constants.TEST_BATTERY_ID);
        this.testBattery.setManufacturer(Constants.TEST_BATTERY_MANUFACTURER);
        this.testBattery.setModel(Constants.TEST_BATTERY_MODEL);
        this.testBattery.setCapacity(Constants.TEST_BATTERY_CAPACITY);
        this.testBattery.setConnectionType(Constants.TEST_BATTERY_CONNECTION_TYPE);
        this.testBattery.setTerminals(Constants.TEST_BATTERY_TERMINALS);
        this.testBattery.setVoltage(Constants.TEST_BATTERY_VOLTAGE);

        this.testController = new ChargeController();
        this.testController.setId(Constants.TEST_CONTROLLER_ID);
        this.testController.setManufacturer(Constants.TEST_CONTROLLER_MANUFACTURER);
        this.testController.setModel(Constants.TEST_CONTROLLER_MODEL);
        this.testController.setCurrent(Constants.TEST_CONTROLLER_CURRENT);
        this.testController.setPower(Constants.TEST_CONTROLLER_POWER);
        this.testController.setVoltage(Constants.TEST_CONTROLLER_VOLTAGE);

        this.testInverter = new Inverter();
        testInverter.setId(Constants.TEST_INVERTER_ID);
        testInverter.setManufacturer(Constants.TEST_INVERTER_MANUFACTURER);
        testInverter.setModel(Constants.TEST_INVERTER_MODEL);
        testInverter.setACPower(Constants.TEST_INVERTER_AC_POWER);
        testInverter.setMaxACPower(Constants.TEST_INVERTER_MAX_AC_POWER);
        testInverter.setMaxPVPower(Constants.TEST_INVERTER_MAX_PV_POWER);

        this.testPanel = new PVPanel();
        testPanel.setId(Constants.TEST_PANEL_ID);
        testPanel.setManufacturer(Constants.TEST_PANEL_MANUFACTURER);
        testPanel.setModel(Constants.TEST_PANEL_MODEL);
        testPanel.setPower(Constants.TEST_PANEL_POWER);
        testPanel.setCurrentAtMaxPower(Constants.TEST_PANEL_CURRENT_MAX_POWER);
        testPanel.setVoltageAtMaxPower(Constants.TEST_PANEL_VOLTAGE_MAX_POWER);
        testPanel.setConnector(Constants.TEST_PANEL_CONNECTOR);

        this.testUser=new User();
        this.testUser.setUsername(Constants.TEST_USER_USERNAME);
        this.testUser.setPassword(Constants.TEST_USER_PASSWORD);

        this.testSolarUnit = new SolarUnit();
        this.testSolarUnit.setId("some_test-id");
        this.testSolarUnit.setBatteryType(this.testBattery);
        this.testSolarUnit.setBatteryCellsCount(3);
        this.testSolarUnit.setChargeController(this.testController);
        this.testSolarUnit.setInverter(this.testInverter);
        this.testSolarUnit.setPanels(this.testPanel);
        this.testSolarUnit.setPanelsCount(10);
        this.testSolarUnit.setInclination(15);
        this.testSolarUnit.setOrientation(25);
        this.testSolarUnit.setUser(this.testUser);


        this.solarUnitService = new SolarUnitServiceImpl(solarUnitRepository,
                panelService,
                controllerService,
                inverterService,
                batteryService,
                userService,
                mockMapper,
                mockValidator);
    }


    @Test
    public void createSolarUnit_withValidParams_shouldReturnSavedUnit() throws InvalidObjectException {
        Mockito.when(this.solarUnitRepository.save(Mockito.any(SolarUnit.class))).
                thenReturn(this.testSolarUnit);

        SolarUnitServiceModel savedUnit = this.solarUnitService.createSolarUnit(
                mockMapper.map(this.testSolarUnit,SolarUnitServiceModel.class));

        assertEquals(this.testSolarUnit.getId(), savedUnit.getId());
        assertEquals(this.testSolarUnit.getPanels().getId(),savedUnit.getPanels().getId());
        assertEquals(this.testSolarUnit.getInverter().getId(),savedUnit.getInverter().getId());
        assertEquals(this.testSolarUnit.getChargeController().getId(),savedUnit.getChargeController().getId());
    }

    @Test
    public void savePanelWithNullParam_shouldNotBeValid(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.solarUnitService=new SolarUnitServiceImpl(solarUnitRepository,
                panelService,
                controllerService,
                inverterService,
                batteryService,
                userService,
                mockMapper,
                validator);
        SolarUnitServiceModel tempUnit = mockMapper.map(this.testSolarUnit, SolarUnitServiceModel.class);
        tempUnit.setPanels(null);
        assertThrows(InvalidObjectException.class, ()->this.solarUnitService.createSolarUnit(tempUnit));
        Set<ConstraintViolation<SolarUnitServiceModel>> violations = validator.validate(tempUnit);

        assertEquals(1,violations.size());
    }

    @Test
    public void findAllByUsernameWithExistingUser_shouldReturnAllUnitsCreatedByCurrentUser(){
        Mockito.when(this.solarUnitRepository.findAllByUsername(Mockito.anyString())).
                thenReturn(List.of(this.testSolarUnit));

        List<SolarUnitServiceModel> allUnitForUser = this.solarUnitService.findAllByUsername(this.testUser.getUsername());

        assertEquals(1, allUnitForUser.size());
        assertEquals(this.testSolarUnit.getId(),allUnitForUser.get(0).getId());
    }

    @Test
    public void findAllByUsername_withNonExistingUser_shouldReturnEmptyList(){
        Mockito.when(this.solarUnitRepository.findAllByUsername(Mockito.anyString())).
                thenReturn(new ArrayList<>());

        List<SolarUnitServiceModel> allUnitForUser = this.solarUnitService.findAllByUsername("no such user");

        assertEquals(0, allUnitForUser.size());
    }

    @Test
    public void findUnitById_withExistingId_shouldReturnFoundUnit(){
        Mockito.when(this.solarUnitRepository.findById(Mockito.anyString())).
                thenReturn(Optional.of(this.testSolarUnit));

        SolarUnitServiceModel foundUnit=this.solarUnitService.findById(this.testSolarUnit.getId());

        assertEquals(this.testSolarUnit.getId(), foundUnit.getId());
        assertEquals(this.testSolarUnit.getChargeController().getVoltage(), foundUnit.getChargeController().getVoltage());

    }

}
