package com.library.common.exception;

/**
 * Application-specific exception with error code and message.
 */
public class AppException extends RuntimeException {

    private final String errorCode;
    private final String message;

    public AppException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public AppException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
