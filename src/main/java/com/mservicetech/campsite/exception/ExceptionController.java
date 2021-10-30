package com.mservicetech.campsite.exception;

import com.mservicetech.campsite.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    public  final String SYSTEM_ERROR_CODE = "ERR10000";
    public  final String DATA_PROCESS_ERROR_CODE = "ERR10001";
    public  final String DATA_NOT_FOUND_ERROR_CODE = "ERR10002";

    @ExceptionHandler(value = ServiceProcessException.class)
    public ResponseEntity<Object> exception(ServiceProcessException exception) {
        return new ResponseEntity<>(new Error().code(SYSTEM_ERROR_CODE).message(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataProcessException.class)
    public ResponseEntity<Object> exception(DataProcessException exception) {
        return new ResponseEntity<>(new Error().code(DATA_PROCESS_ERROR_CODE).message(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> exception(DataNotFoundException exception) {
        return new ResponseEntity<>(new Error().code(DATA_NOT_FOUND_ERROR_CODE).message(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessValidationException.class)
    public ResponseEntity<Object> exception(BusinessValidationException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
