package com.exhibition.app.exception;

public class AuthorisationFailException extends RuntimeException {
    private final int errorCode;

    public AuthorisationFailException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AuthorisationFailException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
