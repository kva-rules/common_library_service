package com.library.common.exception;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super("ERR_401", "Unauthorized access");
    }
    
    public UnauthorizedException(String resource) {
        super("ERR_401", String.format("Unauthorized to access %s", resource));
    }
}

