package com.transunion.pdf.exception;

import lombok.Getter;

@Getter
public class FileNotFoundException extends RuntimeException {

    private final int errorCode;

    public FileNotFoundException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
