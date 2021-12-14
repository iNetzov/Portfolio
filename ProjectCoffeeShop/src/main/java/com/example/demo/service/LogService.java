package com.example.demo.service;

import com.example.demo.models.DTO.LogsDTO;
import com.example.demo.models.entity.LogEntity;

import java.util.List;

public interface LogService {
    void addToLog(LogEntity logEntity);

    void clear();

    List<LogsDTO> getAllLogs();
}
