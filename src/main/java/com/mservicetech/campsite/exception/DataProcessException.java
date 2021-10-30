package com.mservicetech.campsite.exception;

import org.springframework.dao.DataAccessException;

public class DataProcessException extends DataAccessException {

    private static final long serialVersionUID = 1L;


    public DataProcessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataProcessException(String msg) {
        super(msg);
    }
}
