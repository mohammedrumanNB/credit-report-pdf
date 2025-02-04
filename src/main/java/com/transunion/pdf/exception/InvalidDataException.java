package com.transunion.pdf.exception;

public class InvalidDataException extends RuntimeException {
    private final int errorCode;

    public InvalidDataException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}

