package com.example.demo.web;

import com.example.demo.models.DTO.LogsDTO;
import com.example.demo.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogsRestController {
    private final LogService logService;

    public LogsRestController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("/logs/all")
    public ResponseEntity<List<LogsDTO>> getAllLogs(){
        List<LogsDTO>allLogs = logService.getAllLogs();
        return ResponseEntity.ok(allLogs);
    }
}
