package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Log;
import com.ip.fitnessApp.repository.LogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public Log updateLog(Integer id) {
        Log log = logRepository.findById(id)
                .orElse(null);
        if (log == null)
            return null;

        log.setBrojLogova(log.getBrojLogova()+1);
        return logRepository.save(log);
    }
}
