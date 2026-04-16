package com.library.common.constants;

/**
 * Application-wide constants.
 */
public final class AppConstants {
    
    private AppConstants() {} // Prevent instantiation
    
    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;
    
    // Emails
    public static final String SYSTEM_ADMIN_EMAIL = "admin@system.com";
    
    // Headers
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    
    // Other
    public static final String ANONYMOUS_USER = "anonymousUser";
}

