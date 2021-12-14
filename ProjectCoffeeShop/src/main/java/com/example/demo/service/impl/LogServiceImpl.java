package com.example.demo.service.impl;

import com.example.demo.models.DTO.LogsDTO;
import com.example.demo.models.entity.LogEntity;
import com.example.demo.repository.LogRepository;
import com.example.demo.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl  implements LogService {
    private final LogRepository logRepository;
    private final ModelMapper modelMapper;

    public LogServiceImpl(LogRepository logRepository, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addToLog(LogEntity logEntity) {
        logRepository.save(logEntity);
    }

    @Override
    public void clear() {
        logRepository.deleteAll();
    }

    @Override
    public List<LogsDTO> getAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(this::asLogs)
                .collect(Collectors.toList());
    }

    private LogsDTO asLogs(LogEntity log){
        return modelMapper.map(log,LogsDTO.class);
    }

}
