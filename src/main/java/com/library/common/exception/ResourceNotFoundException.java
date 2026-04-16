package com.library.common.exception;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String resourceType, String id) {
        super("ERR_404", String.format("%s not found with id: %s", resourceType, id));
    }
}

