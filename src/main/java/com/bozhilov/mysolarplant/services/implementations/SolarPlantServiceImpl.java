package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.data.models.plant.SolarPlant;
import com.bozhilov.mysolarplant.data.repositories.SolarPlantRepository;
import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.services.services.SolarPlantService;
import com.bozhilov.mysolarplant.services.services.SolarUnitService;
import com.bozhilov.mysolarplant.services.services.UserService;
import com.bozhilov.mysolarplant.utils.Constants;
import com.bozhilov.mysolarplant.web.models.SolarPlantCreateModel;
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
    private final SolarUnitService solarUnitService;
    private final UserService userService;



    @Autowired
    public SolarPlantServiceImpl(SolarPlantRepository solarPlantRepository,
                                 ModelMapper mapper,
                                 Validator validator, SolarUnitService solarUnitService, UserService userService) {
        this.solarPlantRepository = solarPlantRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.solarUnitService = solarUnitService;
        this.userService = userService;
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

    @Override
    public SolarPlantServiceModel convertCreateToServiceModel(SolarPlantCreateModel solarPlantCreateModel) {
        SolarPlantServiceModel solarPlantServiceModel = new SolarPlantServiceModel();
        Location location = new Location();
        location.setTown(solarPlantCreateModel.getTown());
        location.setMunicipality(solarPlantCreateModel.getMunicipality());
        location.setCountry(solarPlantCreateModel.getCountry());
        solarPlantServiceModel.setLocation(location);

        List<SolarUnitServiceModel> solarUnitServiceModelList =
                solarPlantCreateModel.getSolarUnitsIds()
                        .stream()
                        .map(solarUnitService::findById)
                        .collect(Collectors.toList());
        solarPlantServiceModel.setSolarUnits(solarUnitServiceModelList);

        UserServiceModel userServiceModel = userService.findByUsername(solarPlantCreateModel.getUsername());
        solarPlantServiceModel.setUser(userServiceModel);

        return solarPlantServiceModel;
    }
}
