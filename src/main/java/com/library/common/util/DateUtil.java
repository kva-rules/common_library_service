package com.library.common.util;

import com.library.common.exception.InvalidRequestException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Date and time utilities with ISO formats.
 */
public class DateUtil {
    
    public static final String ISO_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ISO_DATE = "yyyy-MM-dd";
    public static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(ISO_TIMESTAMP);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(ISO_DATE);
    
    /**
     * Get current UTC timestamp as LocalDateTime.
     */
    public static LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
    
    /**
     * Format LocalDateTime to ISO string.
     */
    public static String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(TIMESTAMP_FORMATTER);
    }
    
    /**
     * Parse ISO string to LocalDateTime.
     * @throws InvalidRequestException on parse failure.
     */
    public static LocalDateTime parseDate(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            throw new InvalidRequestException("Date string is empty");
        }
        try {
            return LocalDateTime.parse(dateStr, TIMESTAMP_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidRequestException("Invalid date format: " + dateStr + ". Expected: " + ISO_TIMESTAMP);
        }
    }
}

