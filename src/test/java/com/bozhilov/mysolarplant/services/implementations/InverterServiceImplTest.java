package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.Inverter;
import com.bozhilov.mysolarplant.data.repositories.InverterRepository;
import com.bozhilov.mysolarplant.services.models.InverterServiceModel;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InverterServiceImplTest {
    private static final String TEST_INVERTER_ID = "b65f7d51-39aa-4f90-8c63-5d599e9736d9";
    private static final String TEST_INVERTER_MANUFACTURER = "Steca";
    private static final String TEST_INVERTER_MODEL = "StecaGrid 3000";
    private static final Double TEST_INVERTER_AC_POWER = 3000d;
    private static final Double TEST_INVERTER_MAX_AC_POWER = 3000d;
    private static final Double TEST_INVERTER_MAX_PV_POWER = 3800d;

    @Mock
    private InverterRepository mockInverterRepository;
    @Spy
    private ModelMapper mapper;
    @Spy
    private Validator validator;
    @InjectMocks
    private InverterServiceImpl inverterService;
    private Inverter testInverter;

    @BeforeEach
    public void setUp(){
        this.testInverter = new Inverter();
        testInverter.setId(TEST_INVERTER_ID);
        testInverter.setManufacturer(TEST_INVERTER_MANUFACTURER);
        testInverter.setModel(TEST_INVERTER_MODEL);
        testInverter.setACPower(TEST_INVERTER_AC_POWER);
        testInverter.setMaxACPower(TEST_INVERTER_MAX_AC_POWER);
        testInverter.setMaxPVPower(TEST_INVERTER_MAX_PV_POWER);

        this.inverterService = new InverterServiceImpl(mockInverterRepository, mapper, validator);
    }

    @Test
    public void saveInverterWithValidParams_shouldReturnSavedInverter() throws InvalidObjectException {
        Mockito.when(this.mockInverterRepository.save(Mockito.any(Inverter.class)))
                .thenReturn(testInverter);

        InverterServiceModel savedInverter=this.inverterService.
                saveInverter(mapper.map(testInverter, InverterServiceModel.class));

        assertEquals(testInverter.getId(), savedInverter.getId());
        assertEquals(testInverter.getManufacturer(), savedInverter.getManufacturer());
        assertEquals(testInverter.getMaxACPower(), savedInverter.getMaxACPower());
    }

    @Test
    public void saveInverterWithNullParam_shouldNotBeValid(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.inverterService=new InverterServiceImpl(mockInverterRepository, mapper,validator);
        InverterServiceModel tempInverter = mapper.map(this.testInverter, InverterServiceModel.class);
        tempInverter.setACPower(0d);
        assertThrows(InvalidObjectException.class, ()->this.inverterService.saveInverter(tempInverter));
        Set<ConstraintViolation<InverterServiceModel>> violations = validator.validate(tempInverter);

        assertEquals(1,violations.size());
    }

    @Test
    public void allBatteries_shouldReturnListOfBatteries(){
        List<Inverter> test=List.of(testInverter);
        Mockito.when(this.mockInverterRepository.findAll()).
                thenReturn(test);

        List<InverterServiceModel>allInverters=this.inverterService.allInverters();

        assertEquals(1,allInverters.size());
        assertEquals(testInverter.getId(), allInverters.get(0).getId());
    }

    @Test
    public void allBatteries_ifNoBatteries_shouldReturnEmptyListOfBatteries(){
        Mockito.when(this.mockInverterRepository.findAll()).
                thenReturn(new ArrayList<>());

        List<InverterServiceModel>allInverters=this.inverterService.allInverters();

        assertEquals(0,allInverters.size());
    }

    @Test
    public void editInverter_editInverterWithValidParams_shouldReturnEditedInverter() throws InvalidObjectException {
        Inverter editedInverter=testInverter;
        editedInverter.setMaxACPower(1544d);

        Mockito.when(this.mockInverterRepository.saveAndFlush(Mockito.any(Inverter.class))).
                thenReturn(editedInverter);

        this.inverterService.editInverter(mapper.map(editedInverter,InverterServiceModel.class));

        assertEquals(editedInverter.getMaxACPower(), 1544d);
        assertEquals(editedInverter.getId(), testInverter.getId());
        assertEquals(editedInverter.getMaxPVPower(), testInverter.getMaxPVPower());
    }

    @Test
    public void findByIdExistingEntity_shouldReturnEntityWithProvidedId(){
        Mockito.when(this.mockInverterRepository.findById(Mockito.anyString())).
                thenReturn(Optional.of(testInverter));
        InverterServiceModel foundInverter=this.inverterService.findInverterById(testInverter.getId());

        assertEquals(testInverter.getId(), foundInverter.getId());
        assertEquals(testInverter.getMaxACPower(),testInverter.getMaxACPower());
    }

    @Test
    public void findByIdNonExistingEntity_shouldThrow(){
        Mockito.when(this.mockInverterRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.INVERTER_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.inverterService.findInverterById("some_id"));
    }

    @Test
    public void deleteInverter_withValidEntity_shouldCallDeleteMethodOnce(){
        Mockito.when(this.mockInverterRepository.findById(testInverter.getId()))
                .thenReturn(Optional.of(testInverter));
        this.inverterService.deleteInverter(testInverter.getId());
        Mockito.verify(mockInverterRepository, Mockito.times(1)).delete(testInverter);
    }

    @Test
    public void deleteInverter_withInvalidEntity_shouldThrow(){
        Mockito.when(this.mockInverterRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.INVERTER_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.inverterService.deleteInverter(testInverter.getId()));
    }

}
