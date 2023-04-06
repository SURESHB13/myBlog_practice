package com.myBlogPpractice.Exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    private  String message;
    private  String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
