package com.applause.findtesterapp.utils;

public class AppException extends Throwable {
	
    static final long serialVersionUID = 1L;

    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    protected AppException(String message, Throwable cause, 
    		boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}