package com.library.common.exception;

public class ServiceUnavailableException extends BaseException {
    public ServiceUnavailableException(String service) {
        super("ERR_503", String.format("%s service unavailable", service));
    }
    
    public ServiceUnavailableException() {
        super("ERR_503", "Service unavailable");
    }
}

