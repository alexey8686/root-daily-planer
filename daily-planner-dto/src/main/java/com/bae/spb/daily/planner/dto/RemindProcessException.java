package com.bae.spb.daily.planner.dto;

public class RemindProcessException extends RuntimeException{

    public RemindProcessException() {
    }

    public RemindProcessException(String message) {
        super(message);
    }

    public RemindProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
