package com.mservicetech.campsite.exception;

import com.mservicetech.campsite.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ServiceProcessException.class)
    public ResponseEntity<Object> exception(ServiceProcessException exception) {
        return new ResponseEntity<>("Backend service error. Please contact to admin; Error message:" + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataProcessException.class)
    public ResponseEntity<Object> exception(DataProcessException exception) {
        return new ResponseEntity<>(new Error().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> exception(DataNotFoundException exception) {
        return new ResponseEntity<>(new Error().code(HttpStatus.BAD_REQUEST.value()).message(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
