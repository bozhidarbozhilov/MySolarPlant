package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.PVPanel;
import com.bozhilov.mysolarplant.data.repositories.PVPanelRepository;
import com.bozhilov.mysolarplant.services.models.PVPanelServiceModel;
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
public class PVPanelServiceImplTests {
    private static final String TEST_PANEL_ID = "00e1d99d-29af-4f74-8603-6b378514eebj";
    private static final String TEST_PANEL_MANUFACTURER = "MITSUBISHI ELECTRIC";
    private static final String TEST_PANEL_MODEL = "PV-MLU255HC";
    private static final String TEST_PANEL_CONNECTOR = "MC";
    private static final Double TEST_PANEL_POWER = 225d;
    private static final Double TEST_PANEL_VOLTAGE_MAX_POWER = 31.2;
    private static final Double TEST_PANEL_CURRENT_MAX_POWER = 8.18;

    @Mock
    private PVPanelRepository mockPanelRepository;
    @Spy
    private ModelMapper mapper;
    @Spy
    private Validator validator;
    @InjectMocks
    private PVPanelServiceImpl panelService;
    private PVPanel testPanel;

    @BeforeEach
    public void setUp(){
        this.testPanel = new PVPanel();
        testPanel.setId(TEST_PANEL_ID);
        testPanel.setManufacturer(TEST_PANEL_MANUFACTURER);
        testPanel.setModel(TEST_PANEL_MODEL);
        testPanel.setPower(TEST_PANEL_POWER);
        testPanel.setCurrentAtMaxPower(TEST_PANEL_CURRENT_MAX_POWER);
        testPanel.setVoltageAtMaxPower(TEST_PANEL_VOLTAGE_MAX_POWER);
        testPanel.setConnector(TEST_PANEL_CONNECTOR);

        this.panelService = new PVPanelServiceImpl(mockPanelRepository, mapper, validator);
    }

    @Test
    public void savePanelWithValidParams_shouldReturnSavedPanel() throws InvalidObjectException {
        Mockito.when(this.mockPanelRepository.save(Mockito.any(PVPanel.class)))
                .thenReturn(testPanel);

        PVPanelServiceModel savedPanel=this.panelService.
                save(mapper.map(testPanel, PVPanelServiceModel.class));

        assertEquals(testPanel.getId(), savedPanel.getId());
        assertEquals(testPanel.getManufacturer(), savedPanel.getManufacturer());
        assertEquals(testPanel.getPower(), savedPanel.getPower());
    }

    @Test
    public void savePanelWithNullParam_shouldNotBeValid(){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.panelService=new PVPanelServiceImpl(mockPanelRepository, mapper,validator);
        PVPanelServiceModel tempPanel = mapper.map(this.testPanel, PVPanelServiceModel.class);
        tempPanel.setPower(0d);
        assertThrows(InvalidObjectException.class, ()->this.panelService.save(tempPanel));
        Set<ConstraintViolation<PVPanelServiceModel>> violations = validator.validate(tempPanel);

        assertEquals(1,violations.size());
    }

    @Test
    public void allBatteries_shouldReturnListOfBatteries(){
        List<PVPanel> test=List.of(testPanel);
        Mockito.when(this.mockPanelRepository.findAll()).
                thenReturn(test);

        List<PVPanelServiceModel>allPanels=this.panelService.allPanels();

        assertEquals(1,allPanels.size());
        assertEquals(testPanel.getId(), allPanels.get(0).getId());
    }

    @Test
    public void allBatteries_ifNoBatteries_shouldReturnEmptyListOfBatteries(){
        Mockito.when(this.mockPanelRepository.findAll()).
                thenReturn(new ArrayList<>());

        List<PVPanelServiceModel>allPanels=this.panelService.allPanels();

        assertEquals(0,allPanels.size());
    }

    @Test
    public void editPanel_editPanelWithValidParams_shouldReturnEditedPanel() throws InvalidObjectException {
        PVPanel editedPanel=testPanel;
        editedPanel.setPower(1544d);

        Mockito.when(this.mockPanelRepository.saveAndFlush(Mockito.any(PVPanel.class))).
                thenReturn(editedPanel);

        this.panelService.editPanel(mapper.map(editedPanel,PVPanelServiceModel.class));

        assertEquals(editedPanel.getPower(), 1544d);
        assertEquals(editedPanel.getId(), testPanel.getId());
        assertEquals(editedPanel.getConnector(), testPanel.getConnector());
    }

    @Test
    public void findByIdExistingEntity_shouldReturnEntityWithProvidedId(){
        Mockito.when(this.mockPanelRepository.findById(Mockito.anyString())).
                thenReturn(Optional.of(testPanel));
        PVPanelServiceModel foundPanel=this.panelService.findPanelById(testPanel.getId());

        assertEquals(testPanel.getId(), foundPanel.getId());
        assertEquals(testPanel.getPower(),testPanel.getPower());
    }

    @Test
    public void findByIdNonExistingEntity_shouldThrow(){
        Mockito.when(this.mockPanelRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.PV_PANEL_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.panelService.findPanelById("some_id"));
    }

    @Test
    public void deletePanel_withValidEntity_shouldCallDeleteMethodOnce(){
        Mockito.when(this.mockPanelRepository.findById(testPanel.getId()))
                .thenReturn(Optional.of(testPanel));
        this.panelService.deletePanel(testPanel.getId());
        Mockito.verify(mockPanelRepository, Mockito.times(1)).delete(testPanel);
    }

    @Test
    public void deletePanel_withInvalidEntity_shouldThrow(){
        Mockito.when(this.mockPanelRepository.findById(Mockito.anyString())).
                thenThrow(new IllegalArgumentException(Constants.PV_PANEL_NOT_FOUND));

        assertThrows(IllegalArgumentException.class,
                ()->this.panelService.deletePanel(testPanel.getId()));
    }

}
