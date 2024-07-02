package com.ip.fitnessApp.exceptions;

import com.ip.fitnessApp.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ExceptionHandler annotated method can only handle the exceptions thrown by that particular class.
//However, if we want to handle any exception thrown throughout the application we can define a global
// exception handler class and annotate it with @ControllerAdvice.This annotation helps to integrate
// multiple exception handlers into a single global unit.
// Scenario: u user servisu se baca izuzetak RecordNotFoundException ako user pokusa da pronadje zapis u bazi
// na osnovu id-a koji ne postoji. Taj izuzetak ce uhvatiti ova klasa i pozvati odgovarajuci metod.
// U metodama ove klase se prvo kreira Log objekat pomocu poziva log servisa i njegovog metoda createLog(). Na taj
// nacin se Log objekat smjesta u bazu podataka. 

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
