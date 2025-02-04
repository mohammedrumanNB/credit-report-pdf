package com.transunion.pdf.exception;

public class FileNotFoundException extends RuntimeException {

    private final int errorCode;

    public FileNotFoundException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
