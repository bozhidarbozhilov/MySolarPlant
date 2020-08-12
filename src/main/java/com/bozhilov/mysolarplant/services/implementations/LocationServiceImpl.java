package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.other.Location;
import com.bozhilov.mysolarplant.data.repositories.LocationRepository;
import com.bozhilov.mysolarplant.services.models.LocationServiceModel;
import com.bozhilov.mysolarplant.services.services.LocationService;
import com.bozhilov.mysolarplant.utils.Constants;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper mapper, Validator validator) {
        this.locationRepository = locationRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public LocationServiceModel saveLocation(LocationServiceModel locationServiceModel) {
        if(validator.validate(locationServiceModel).size()>0){
            throw new IllegalArgumentException(Constants.INVALID_LOCATION_PARAMS);
        }

        Location location = mapper.map(locationServiceModel, Location.class);
        Location savedLocation = locationRepository.save(location);

        return mapper.map(savedLocation, LocationServiceModel.class);
    }
}
