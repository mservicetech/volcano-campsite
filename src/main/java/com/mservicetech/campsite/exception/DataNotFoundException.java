package com.mservicetech.campsite.exception;

import org.springframework.dao.DataAccessException;

public class DataNotFoundException extends DataAccessException {

    private static final long serialVersionUID = 1L;


    public DataNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }
}
