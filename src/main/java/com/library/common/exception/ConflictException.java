package com.library.common.exception;

public class ConflictException extends BaseException {
    public ConflictException(String resource) {
        super("ERR_409", String.format("%s conflict", resource));
    }
    
    public ConflictException() {
        super("ERR_409", "Conflict occurred");
    }
}

