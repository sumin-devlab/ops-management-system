package com.example.opsmanagement.service;

import com.example.opsmanagement.entity.Log;
import com.example.opsmanagement.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void saveLog(String message, String level) {
        Log log = new Log();
        log.setMessage(message);
        log.setLevel(level);
        log.setCreatedAt(LocalDateTime.now());

        logRepository.save(log);
    }
}