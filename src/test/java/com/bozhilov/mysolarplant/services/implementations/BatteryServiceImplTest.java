package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.other.ConnectionType;
import com.bozhilov.mysolarplant.data.models.other.Terminals;
import com.bozhilov.mysolarplant.data.models.plant.Battery;
import com.bozhilov.mysolarplant.data.repositories.BatteryRepository;
import com.bozhilov.mysolarplant.services.models.BatteryServiceModel;
import com.bozhilov.mysolarplant.services.services.BatteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.validation.Validator;
import java.io.InvalidObjectException;

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
    private Validator validator;
    @InjectMocks
    private BatteryServiceImpl batteryService;
    private Battery testBattery;

    @BeforeEach
    public void init(){
        this.testBattery = new Battery();
        this.testBattery.setId(TEST_BATTERY_ID);
        this.testBattery.setManufacturer(TEST_BATTERY_MANUFACTURER);
        this.testBattery.setModel(TEST_BATTERY_MODEL);
        this.testBattery.setCapacity(TEST_BATTERY_CAPACITY);
        this.testBattery.setConnectionType(TEST_BATTERY_CONNECTION_TYPE);
        this.testBattery.setTerminals(TEST_BATTERY_TERMINALS);
        this.testBattery.setVoltage(TEST_BATTERY_VOLTAGE);
        this.batteryService=new BatteryServiceImpl(mockBatteryRepository, mockMapper,validator);
    }

    @Test
    public void batteryService_saveBatteryWithValidParameters_returnSavedBattery() throws InvalidObjectException {
        Mockito.when(this.mockBatteryRepository.save(Mockito.any(Battery.class)))
                .thenReturn(this.testBattery);
        BatteryServiceModel temp_battery= mockMapper.map(testBattery, BatteryServiceModel.class);

        BatteryServiceModel savedBattery = batteryService.saveBattery(temp_battery);

        assertSame(testBattery.getId(), savedBattery.getId());

    }

}