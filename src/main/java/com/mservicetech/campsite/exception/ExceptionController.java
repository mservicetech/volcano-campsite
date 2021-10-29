package com.mservicetech.campsite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ServiceProcessException.class)
    public ResponseEntity<Object> exception(ServiceProcessException exception) {
        return new ResponseEntity<>("Backend service error. Please contact to admin", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
