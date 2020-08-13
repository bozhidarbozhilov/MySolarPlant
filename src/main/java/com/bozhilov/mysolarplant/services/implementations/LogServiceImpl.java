package com.bozhilov.mysolarplant.services.implementations;

import com.bozhilov.mysolarplant.data.models.other.Log;
import com.bozhilov.mysolarplant.data.repositories.LogRepository;
import com.bozhilov.mysolarplant.services.models.LogServiceModel;
import com.bozhilov.mysolarplant.services.services.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final ModelMapper mapper;

    public LogServiceImpl(LogRepository logRepository, ModelMapper mapper) {
        this.logRepository = logRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveLog(LogServiceModel logServiceModel) {
         logRepository.saveAndFlush(mapper.map(logServiceModel,Log.class));
    }
}
