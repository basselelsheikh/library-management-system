package com.example.library.exception;

public class LibraryException extends RuntimeException {
    private final String errorCode;

    public LibraryException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}