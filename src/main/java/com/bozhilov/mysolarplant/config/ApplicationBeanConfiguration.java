package com.bozhilov.mysolarplant.config;

import com.bozhilov.mysolarplant.data.models.users.Role;
import com.bozhilov.mysolarplant.services.models.SolarPlantServiceModel;
import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.services.models.UserServiceModel;
import com.bozhilov.mysolarplant.web.models.AllUsersViewModel;
import com.bozhilov.mysolarplant.web.models.SolarPlantAllViewModel;
import com.bozhilov.mysolarplant.web.models.SolarPlantDetailsModel;
import com.bozhilov.mysolarplant.web.models.SolarUnitAllViewModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<SolarUnitServiceModel, SolarUnitAllViewModel> solarUnitTypeMap =
                modelMapper.createTypeMap(SolarUnitServiceModel.class, SolarUnitAllViewModel.class);

        solarUnitTypeMap
                .addMapping(src->src.getBatteryType().getModel(), SolarUnitAllViewModel::setBatteryTypeModel)
                .addMapping(SolarUnitServiceModel::getBatteryCellsCount, SolarUnitAllViewModel::setBatteryCellsCount)
                .addMapping(src->src.getChargeController().getModel(), SolarUnitAllViewModel::setChargeControllerModel)
                .addMapping(src->src.getInverter().getModel(), SolarUnitAllViewModel::setInverterModel)
                .addMapping(src->src.getPanels().getModel(), SolarUnitAllViewModel::setPanelsModel)
                .addMapping(SolarUnitServiceModel::getPanelsCount, SolarUnitAllViewModel::setPanelsCount)
                .addMapping(SolarUnitServiceModel::getInclination, SolarUnitAllViewModel::setInclination)
                .addMapping(SolarUnitServiceModel::getOrientation, SolarUnitAllViewModel::setOrientation)
                .addMapping(SolarUnitServiceModel::getId, SolarUnitAllViewModel::setId);

        TypeMap<SolarPlantServiceModel, SolarPlantAllViewModel> plantServiceToAllViewTypeMap =
                modelMapper.createTypeMap(SolarPlantServiceModel.class, SolarPlantAllViewModel.class);

        plantServiceToAllViewTypeMap
                .addMapping(src->src.getLocation().getTown(), SolarPlantAllViewModel::setTown)
                .addMapping(src->src.getLocation().getMunicipality(),SolarPlantAllViewModel::setMunicipality)
                .addMapping(src->src.getLocation().getCountry(), SolarPlantAllViewModel::setCountry)
                .addMapping(SolarPlantServiceModel::getId, SolarPlantAllViewModel::setId)
                .addMapping(src->src.getSolarUnits().size(), SolarPlantAllViewModel::setSolarUnitsCount);

        TypeMap<SolarPlantServiceModel, SolarPlantDetailsModel> plantServiceToDetailsTypeMap =
                modelMapper.createTypeMap(SolarPlantServiceModel.class, SolarPlantDetailsModel.class);
        plantServiceToDetailsTypeMap
                .addMapping(src->src.getLocation().getTown(), SolarPlantDetailsModel::setTown)
                .addMapping(src->src.getLocation().getMunicipality(),SolarPlantDetailsModel::setMunicipality)
                .addMapping(src->src.getLocation().getCountry(), SolarPlantDetailsModel::setCountry)
                .addMapping(SolarPlantServiceModel::getId, SolarPlantDetailsModel::setId)
                .addMapping(SolarPlantServiceModel::getSolarUnits, SolarPlantDetailsModel::setSolarUnits);

//        Converter<Set<Role>, Set<String>> roleToStringSet =
//                ctx->ctx.getSource()
//                        .stream()
//                        .map(role -> role.getAuthority())
//                        .collect(Collectors.toSet());
//        TypeMap<UserServiceModel, AllUsersViewModel> userServiceToAllView =
//                modelMapper.createTypeMap(UserServiceModel.class, AllUsersViewModel.class);
//        PropertyMap<UserServiceModel, AllUsersViewModel> propertyMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                map().setAuthorities(source.getAuthorities()
//                        .stream()
//                        .map(role -> role.getAuthority())
//                        .collect(Collectors.toSet())
//                );
//                map().setUsername(source.getUsername());
//            }
//
//        };
//        modelMapper.addMappings(propertyMap);

        return modelMapper;
    }

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
