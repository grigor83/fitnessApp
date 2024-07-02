package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Log;
import com.ip.fitnessApp.repository.LogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> findAll(){
        return logRepository.findAll();
    }

    public void saveLog(String message, String logger) {
        Log newLog = new Log();
        newLog.setMessage(message);
        newLog.setDate(LocalDateTime.now());
        newLog.setLogger(logger);

        logRepository.save(newLog);
    }
}
