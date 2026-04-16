package com.library.common.exception;

public class InvalidRequestException extends BaseException {
    public InvalidRequestException(String field) {
        super("ERR_400", String.format("Invalid request: %s", field));
    }
}

