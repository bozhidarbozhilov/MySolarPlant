package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.ChargeController;
import com.bozhilov.mysolarplant.data.repositories.ChargeControllerRepository;
import com.bozhilov.mysolarplant.services.models.ChargeControllerServiceModel;
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
public class ChargeControllerServiceImplTests {
    private static final String TEST_CONTROLLER_ID = "b65f7d51-39aa-4f90-8c63-5d599e9736d9";
    private static final String TEST_CONTROLLER_MANUFACTURER = "Steca";
    private static final String TEST_CONTROLLER_MODEL = "StecaGrid 3000";
    private static final Double TEST_CONTROLLER_CURRENT= 10d;
    private static final Double TEST_CONTROLLER_POWER = 720d;
    private static final Double TEST_CONTROLLER_VOLTAGE = 12d;

    @Mock
    private ChargeControllerRepository mockControllerRepository;
    @Spy
    private ModelMapper mapper;
    @Spy
    private Validator validator;
    @InjectMocks
    private ChargeControllerServiceImpl inverterService;
    private ChargeController testController;

    @BeforeEach
    public void setUp(){
        this.testController = new ChargeController();
        testController.setId(TEST_CONTROLLER_ID);
        testController.setManufacturer(TEST_CONTROLLER_MANUFACTURER);
        testController.setModel(TEST_CONTROLLER_MODEL);
        testController.setCurrent(TEST_CONTROLLER_CURRENT);
        testController.setPower(TEST_CONTROLLER_POWER);
        testController.setVoltage(TEST_CONTROLLER_VOLTAGE);

        this.inverterService = new ChargeControllerServiceImpl(mockControllerRepository, mapper, validator);
    }

    @Test
    public void saveControllerWithValidParams_shouldReturnSavedController() throws InvalidObjectException {
        Mockito.when(this.mockControllerRepository.save(Mockito.any(ChargeController.class)))
                .thenReturn(testController);

       ChargeControllerServiceModel savedController=this.inverterService.
                saveController(mapper.map(testController, ChargeControllerServiceModel.class));

        assertEquals(testController.getId(), savedController.getId());
        assertEquals(testController.getManufacturer(), savedController.getManufacturer());
        assertEquals(testController.getVoltage(), savedController.getVoltage());
    }

    @Test
    public void saveControllerWithNullParam_shouldNotBeValid(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.inverterService=new ChargeControllerServiceImpl(mockControllerRepository, mapper,validator);
        ChargeControllerServiceModel tempController = mapper.map(this.testController, ChargeControllerServiceModel.class);
        tempController.setPower(0d);
        assertThrows(InvalidObjectException.class, ()->this.inverterService.saveController(tempController));
        Set<ConstraintViolation<ChargeControllerServiceModel>> violations = validator.validate(tempController);

        assertEquals(1,violations.size());
    }

    @Test
    public void allBatteries_shouldReturnListOfBatteries(){
        List<ChargeController> test=List.of(testController);
        Mockito.when(this.mockControllerRepository.findAll()).
                thenReturn(test);

        List<ChargeControllerServiceModel>allControllers=this.inverterService.allControllers();

        assertEquals(1,allControllers.size());
        assertEquals(testController.getId(), allControllers.get(0).getId());
    }

    @Test
    public void allBatteries_ifNoBatteries_shouldReturnEmptyListOfBatteries(){
        Mockito.when(this.mockControllerRepository.findAll()).
                thenReturn(new ArrayList<>());

        List<ChargeControllerServiceModel>allControllers=this.inverterService.allControllers();

        assertEquals(0,allControllers.size());
    }

    @Test
    public void editController_editControllerWithValidParams_shouldReturnEditedController() throws InvalidObjectException {
        ChargeController editedController=testController;
        editedController.setPower(1544d);

        Mockito.when(this.mockControllerRepository.saveAndFlush(Mockito.any(ChargeController.class))).
                thenReturn(editedController);

        this.inverterService.editController(mapper.map(editedController,ChargeControllerServiceModel.class));

        assertEquals(editedController.getPower(), 1544d);
        assertEquals(editedController.getId(), testController.getId());
        assertEquals(editedController.getVoltage(), testController.getVoltage());
    }

    @Test
    public void findByIdExistingEntity_shouldReturnEntityWithProvidedId(){
        Mockito.when(this.mockControllerRepository.findById(Mockito.anyString())).
                thenReturn(Optional.of(testController));
        ChargeControllerServiceModel foundController=this.inverterService.findControllerById(testController.getId());

        assertEquals(testController.getId(), foundController.getId());
        assertEquals(testController.getPower(),testController.getPower());
    }

    @Test
    public void findByIdNonExistingEntity_shouldThrow(){
        Mockito.when(this.mockControllerRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.CONTROLLER_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.inverterService.findControllerById("some_id"));
    }

    @Test
    public void deleteController_withValidEntity_shouldCallDeleteMethodOnce(){
        Mockito.when(this.mockControllerRepository.findById(testController.getId()))
                .thenReturn(Optional.of(testController));
        this.inverterService.deleteController(testController.getId());
        Mockito.verify(mockControllerRepository, Mockito.times(1)).delete(testController);
    }

    @Test
    public void deleteController_withInvalidEntity_shouldThrow(){
        Mockito.when(this.mockControllerRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.CONTROLLER_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.inverterService.deleteController(testController.getId()));
    }

}
