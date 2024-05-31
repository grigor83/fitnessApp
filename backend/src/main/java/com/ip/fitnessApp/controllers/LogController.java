package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Log;
import com.ip.fitnessApp.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/logs")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<List<Log>> findAll() {
        List<Log> logs = logService.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }
}
