package com.nimacode.exception;

import java.time.LocalDate;

public class ErrorDetails {
    // timestamp
    private LocalDate timestamp;
    // error message
    private String message;
    // details
    private String details;

    // ResponseEntiyExceptopnHandler.class is Spring class handling error structure.

    public ErrorDetails(LocalDate timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    } // cstor

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
