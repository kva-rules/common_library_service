package com.library.common.exception;

public class AccessDeniedException extends BaseException {
    public AccessDeniedException(String resource) {
        super("ERR_403", String.format("Access denied to %s", resource));
    }
    
    public AccessDeniedException() {
        super("ERR_403", "Access denied");
    }
}

