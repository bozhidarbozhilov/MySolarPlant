package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.other.ConnectionType;
import com.bozhilov.mysolarplant.data.models.other.Terminals;
import com.bozhilov.mysolarplant.data.models.plant.Battery;
import com.bozhilov.mysolarplant.data.repositories.BatteryRepository;
import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BatteryServiceImplTest {
    private static final String TEST_BATTERY_ID = "a031a0c2-6847-4189-a72a-51ed76dcafb6";
    private static final String TEST_BATTERY_MANUFACTURER = "MONBAT MVR";
    private static final String TEST_BATTERY_MODEL = "12MVR65TA";
    private static final Double TEST_BATTERY_CAPACITY = 70d;
    private static final ConnectionType TEST_BATTERY_CONNECTION_TYPE = ConnectionType.valueOf("PARALLEL");
    private static final Terminals TEST_BATTERY_TERMINALS = Terminals.valueOf("FRONT");
    private static final Double TEST_BATTERY_VOLTAGE = 12d;

    @Mock
    private BatteryRepository mockBatteryRepository;
    @Spy
    private ModelMapper mockMapper;
    @Spy
    private Validator mockValidator;
    @InjectMocks
    private BatteryServiceImpl batteryService;
    private Battery testBattery;

    @BeforeEach
    public void setUp(){
        this.testBattery = new Battery();
        this.testBattery.setId(TEST_BATTERY_ID);
        this.testBattery.setManufacturer(TEST_BATTERY_MANUFACTURER);
        this.testBattery.setModel(TEST_BATTERY_MODEL);
        this.testBattery.setCapacity(TEST_BATTERY_CAPACITY);
        this.testBattery.setConnectionType(TEST_BATTERY_CONNECTION_TYPE);
        this.testBattery.setTerminals(TEST_BATTERY_TERMINALS);
        this.testBattery.setVoltage(TEST_BATTERY_VOLTAGE);

        this.batteryService = new BatteryServiceImpl(this.mockBatteryRepository, mockMapper,mockValidator);
    }

    @Test
    public void saveBatteryWithValidParameters_returnSavedBattery() throws InvalidObjectException {
        Mockito.when(this.mockBatteryRepository.save(Mockito.any(Battery.class))).
                thenReturn(this.testBattery);
        BatteryServiceModel temp_battery= mockMapper.map(testBattery, BatteryServiceModel.class);

        BatteryServiceModel savedBattery = batteryService.saveBattery(temp_battery);

        assertSame(testBattery.getId(), savedBattery.getId());
    }

    @Test
    public void saveBatteryWithNullParam_shouldNotBeValid(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.batteryService=new BatteryServiceImpl(mockBatteryRepository, mockMapper,validator);
        BatteryServiceModel temp_battery = mockMapper.map(this.testBattery, BatteryServiceModel.class);
        temp_battery.setTerminals(null);

        assertThrows(InvalidObjectException.class, ()->this.batteryService.saveBattery(temp_battery));

        Set<ConstraintViolation<BatteryServiceModel>> violations = validator.validate(temp_battery);
        assertEquals(1,violations.size());
    }

    @Test
    public void allBatteries_shouldReturnListOfBatteries(){
        List<Battery> test=List.of(testBattery);
        Mockito.when(this.mockBatteryRepository.findAll()).
                thenReturn(test);

        List<BatteryServiceModel>allBatteries=this.batteryService.allBatteries();

        assertEquals(1,allBatteries.size());
        assertEquals(testBattery.getId(), allBatteries.get(0).getId());
    }

    @Test
    public void allBatteries_ifNoBatteries_shouldReturnEmptyListOfBatteries(){
        Mockito.when(this.mockBatteryRepository.findAll()).
                thenReturn(new ArrayList<>());

        List<BatteryServiceModel>allBatteries=this.batteryService.allBatteries();

        assertEquals(0,allBatteries.size());
    }

    @Test
    public void editBattery_editBatteryWithValidParams_shouldReturnEditedBattery() throws InvalidObjectException {
        Battery editedBattery=testBattery;
        editedBattery.setVoltage(36.0);

        Mockito.when(this.mockBatteryRepository.saveAndFlush(Mockito.any(Battery.class))).
                thenReturn(editedBattery);

        this.batteryService.editBattery(mockMapper.map(editedBattery,BatteryServiceModel.class));

        assertEquals(editedBattery.getVoltage(), 36.0);
        assertEquals(editedBattery.getId(), testBattery.getId());
        assertEquals(editedBattery.getConnectionType(), testBattery.getConnectionType());
    }

    @Test
    public void findByIdExistingEntity_shouldReturnEntityWithProvidedId(){
        Mockito.when(this.mockBatteryRepository.findById(Mockito.anyString())).
                thenReturn(Optional.of(testBattery));
        BatteryServiceModel foundBattery=this.batteryService.findBatteryById(testBattery.getId());

        assertEquals(testBattery.getId(), foundBattery.getId());
        assertEquals(testBattery.getCapacity(),testBattery.getCapacity());
    }

    @Test
    public void findByIdNonExistingEntity_shouldThrow(){
        Mockito.when(this.mockBatteryRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.BATTERY_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.batteryService.findBatteryById("some_id"));
    }

    @Test
    public void deleteBattery_withValidEntity_shouldCallDeleteMethodOnce(){
        Mockito.when(this.mockBatteryRepository.findById(testBattery.getId()))
                .thenReturn(Optional.of(testBattery));
        this.batteryService.deleteBattery(testBattery.getId());
        Mockito.verify(mockBatteryRepository, Mockito.times(1)).delete(testBattery);
    }

    @Test
    public void deleteBattery_withInvalidEntity_shouldThrow(){
        Mockito.when(this.mockBatteryRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.BATTERY_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.batteryService.deleteBattery(testBattery.getId()));
    }
}
