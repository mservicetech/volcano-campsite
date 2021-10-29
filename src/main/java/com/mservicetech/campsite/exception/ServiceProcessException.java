package com.mservicetech.campsite.exception;

public class ServiceProcessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ServiceProcessException() {
        super();
    }

    public ServiceProcessException(String message) {
        super(message);
    }

    public ServiceProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceProcessException(Throwable cause) {
        super(cause);
    }
}
