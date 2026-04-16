package com.library.common.util;

import com.library.common.enums.DifficultyLevel;
import com.library.common.exception.InvalidRequestException;

import java.util.regex.Pattern;

/**
 * Common validation utilities.
 */
public class ValidationUtil {
    
    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    
    /**
     * Validate if string is valid UUID.
     * @throws InvalidRequestException if invalid.
     */
    public static void validateUUID(String uuid) {
        if (uuid == null || !UUID_PATTERN.matcher(uuid).matches()) {
            throw new InvalidRequestException("Invalid UUID format: " + uuid);
        }
    }
    
    /**
     * Validate if string is valid email.
     * @throws InvalidRequestException if invalid.
     */
    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidRequestException("Invalid email format: " + email);
        }
    }
    
    /**
     * Validate pagination params: page >=0, size >0 && <=100.
     */
    public static void validatePagination(int page, int size) {
        if (page < 0) {
            throw new InvalidRequestException("Page must be >= 0");
        }
        if (size <= 0 || size > 100) {
            throw new InvalidRequestException("Page size must be >0 and <=100");
        }
    }
    
    /**
     * Validate DifficultyLevel enum.
     */
    public static void validateDifficultyLevel(String level) {
        try {
            DifficultyLevel.valueOf(level.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidRequestException("Invalid difficulty level: " + level + ". Expected: " + DifficultyLevel.values());
        }
    }
}

