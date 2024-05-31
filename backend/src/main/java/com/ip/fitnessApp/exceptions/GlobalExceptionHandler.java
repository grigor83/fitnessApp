package com.ip.fitnessApp.exceptions;

import com.ip.fitnessApp.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final LogService logService;

    public GlobalExceptionHandler(LogService logService) {
        this.logService = logService;
    }

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> handleInvalidId(RecordNotFoundException ex)
    {
        logService.saveLog(ex.getMessage(), String.valueOf(ex.getClass()));
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleDuplicateUsername(UsernameAlreadyExistsException ex)
    {
        logService.saveLog(ex.getMessage(), String.valueOf(ex.getClass()));
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }


}
