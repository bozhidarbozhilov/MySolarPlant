package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.PVPanel;
import com.bozhilov.mysolarplant.data.repositories.PVPanelRepository;
import com.bozhilov.mysolarplant.services.models.PVPanelServiceModel;
import com.bozhilov.mysolarplant.services.services.PVPanelService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PVPanelServiceImpl implements PVPanelService {
    private final PVPanelRepository pvPanelRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public PVPanelServiceImpl(PVPanelRepository pvPanelRepository, ModelMapper mapper, Validator validator) {
        this.pvPanelRepository = pvPanelRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public PVPanelServiceModel save(PVPanelServiceModel pvPanelServiceModel) throws InvalidObjectException {
        if(validator.validate(pvPanelServiceModel).size()>0){
            throw new InvalidObjectException(Constants.INVALID_PV_PANEL_PROPERTIES);
        }
        PVPanel pvPanel = mapper.map(pvPanelServiceModel, PVPanel.class);
        PVPanel savedPanel = this.pvPanelRepository.save(pvPanel);
        return mapper.map(savedPanel, PVPanelServiceModel.class);
    }

    @Override
    public List<PVPanelServiceModel> allPanels() {
        List<PVPanelServiceModel> allPVPanels = pvPanelRepository
                .findAll()
                .stream()
                .map(pvPanel -> mapper.map(pvPanel, PVPanelServiceModel.class))
                .collect(Collectors.toUnmodifiableList());

        return allPVPanels;
    }
}
