package com.library.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {

    @Test
    void baseException() {
        BaseException ex = new BaseException("ERR_500", "Internal server error");
        assertEquals("ERR_500", ex.getErrorCode());
        assertEquals("Internal server error", ex.getMessage());
    }

    @Test
    void resourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("User", "123");
        assertEquals("ERR_404", ex.getErrorCode());
        assertEquals("User not found with id: 123", ex.getMessage());
    }

    @Test
    void unauthorizedException() {
        UnauthorizedException ex1 = new UnauthorizedException();
        assertEquals("ERR_401", ex1.getErrorCode());
        assertEquals("Unauthorized access", ex1.getMessage());

        UnauthorizedException ex2 = new UnauthorizedException("admin panel");
        assertEquals("ERR_401", ex2.getErrorCode());
        assertEquals("Unauthorized to access admin panel", ex2.getMessage());
    }

    @Test
    void conflictExceptionTest() {
        ConflictException ex = new ConflictException("User email");
        assertEquals("ERR_409", ex.getErrorCode());
        assertEquals("User email conflict", ex.getMessage());
    }

    @Test
    void serviceUnavailableExceptionTest() {
        ServiceUnavailableException ex = new ServiceUnavailableException("Payment service");
        assertTrue(ex.getErrorCode().startsWith("ERR_503"));
        assertTrue(ex.getMessage().contains("Payment") && ex.getMessage().contains("unavailable"));
    }
}
