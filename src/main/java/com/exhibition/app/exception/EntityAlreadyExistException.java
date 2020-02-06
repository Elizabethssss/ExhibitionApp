package com.exhibition.app.exception;

public class EntityAlreadyExistException extends RuntimeException {
    private final int errorCode;

    public EntityAlreadyExistException(int errorCode) {
        this.errorCode = errorCode;
    }

    public EntityAlreadyExistException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
