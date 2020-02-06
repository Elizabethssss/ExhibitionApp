package com.exhibition.app.exception;

public class InvalidUserInputException extends RuntimeException {
    private final int errorCode;

    public InvalidUserInputException(int errorCode) {
        this.errorCode = errorCode;
    }

    public InvalidUserInputException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
