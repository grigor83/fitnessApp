package com.ip.fitnessApp.controllers;

import com.ip.fitnessApp.model.Log;
import com.ip.fitnessApp.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/logs")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Log> updateLog(@PathVariable Integer id) {
        Log updatedLog = logService.updateLog(id);

        if (updatedLog != null)
            return new ResponseEntity<>(updatedLog, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }
}
