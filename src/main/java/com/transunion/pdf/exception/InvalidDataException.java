package com.transunion.pdf.exception;

import lombok.Getter;

@Getter
public class InvalidDataException extends RuntimeException {
    private final int errorCode;

    public InvalidDataException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}

