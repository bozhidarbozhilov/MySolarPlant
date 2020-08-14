package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.plant.SolarPlant;
import com.bozhilov.mysolarplant.data.repositories.SolarPlantRepository;
import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.services.SolarPlantService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SolarPlantServiceImpl implements SolarPlantService {
    private final SolarPlantRepository solarPlantRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public SolarPlantServiceImpl(SolarPlantRepository solarPlantRepository,
                                 ModelMapper mapper,
                                 Validator validator) {
        this.solarPlantRepository = solarPlantRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public SolarPlantServiceModel createSolarPlant(SolarPlantServiceModel solarPlantServiceModel) {
        if(validator.validate(solarPlantServiceModel).size()>0){
            throw new IllegalArgumentException("Solar Plant Illegal arguments.");
        }

        SolarPlant solarPlant = mapper.map(solarPlantServiceModel, SolarPlant.class);
        SolarPlant savedPlant = solarPlantRepository.save(solarPlant);

        return mapper.map(savedPlant, SolarPlantServiceModel.class);
    }

    @Override
    public List<SolarPlantServiceModel> findAllByUsername(String username) {
        List<SolarPlant> solarPlants = solarPlantRepository.findAllByUsername(username);

        return solarPlants
                .stream()
                .map(solarPlant -> mapper.map(solarPlant, SolarPlantServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SolarPlantServiceModel findById(String id) {
        SolarPlant solarPlant = solarPlantRepository
                .findById(id).orElseThrow(()->new IllegalArgumentException(Constants.SOLAR_UNIT_NOT_FOUND));

        return mapper.map(solarPlant, SolarPlantServiceModel.class);
    }

    @Override
    public List<SolarPlantServiceModel> findAll() {
        return solarPlantRepository
                .findAll()
                .stream()
                .map(solarPlant -> mapper.map(solarPlant, SolarPlantServiceModel.class))
                .collect(Collectors.toList());
    }
}
