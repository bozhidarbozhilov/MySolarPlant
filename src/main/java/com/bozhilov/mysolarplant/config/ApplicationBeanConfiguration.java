package com.bozhilov.mysolarplant.config;

import com.bozhilov.mysolarplant.services.models.SolarUnitServiceModel;
import com.bozhilov.mysolarplant.web.models.SolarUnitAllViewModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<SolarUnitServiceModel, SolarUnitAllViewModel> typeMap =
                modelMapper.createTypeMap(SolarUnitServiceModel.class, SolarUnitAllViewModel.class);

        typeMap
                .addMapping(src->src.getBatteryType().getModel(), SolarUnitAllViewModel::setBatteryTypeModel)
                .addMapping(SolarUnitServiceModel::getBatteryCellsCount, SolarUnitAllViewModel::setBatteryCellsCount)
                .addMapping(src->src.getChargeController().getModel(), SolarUnitAllViewModel::setChargeControllerModel)
                .addMapping(src->src.getInverter().getModel(), SolarUnitAllViewModel::setInverterModel)
                .addMapping(src->src.getPanels().getModel(), SolarUnitAllViewModel::setPanelsModel)
                .addMapping(SolarUnitServiceModel::getPanelsCount, SolarUnitAllViewModel::setPanelsCount)
                .addMapping(SolarUnitServiceModel::getInclination, SolarUnitAllViewModel::setInclination)
                .addMapping(SolarUnitServiceModel::getOrientation, SolarUnitAllViewModel::setOrientation)
                .addMapping(SolarUnitServiceModel::getId, SolarUnitAllViewModel::setId);
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
