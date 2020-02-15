package com.exhibition.app.exception;

public class FailException extends RuntimeException {
    private final ErrorTypes error;

    public FailException(ErrorTypes error) {
        this.error = error;
    }

    public FailException(String message, ErrorTypes error) {
        super(message);
        this.error = error;
    }

    public ErrorTypes getErrorCode() {
        return error;
    }
}
