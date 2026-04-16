package com.library.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Structured logging utility for consistent logs across services.
 */
public class LoggingUtil {
    
    private static final Logger log = LoggerFactory.getLogger(LoggingUtil.class);
    
    public static void logInfo(String traceId, String userId, String serviceName, String message) {
        MDC.put("traceId", traceId);
        MDC.put("userId", userId);
        MDC.put("serviceName", serviceName);
        MDC.put("timestamp", LocalDateTime.now().toString());
        log.info(message);
        MDC.clear();
    }
    
    public static void logError(String traceId, String userId, String serviceName, String message, Throwable ex) {
        MDC.put("traceId", traceId);
        MDC.put("userId", userId);
        MDC.put("serviceName", serviceName);
        MDC.put("timestamp", LocalDateTime.now().toString());
        log.error(message, ex);
        MDC.clear();
    }
    
    public static void logDebug(String traceId, String userId, String serviceName, String message) {
        MDC.put("traceId", traceId);
        MDC.put("userId", userId);
        MDC.put("serviceName", serviceName);
        MDC.put("timestamp", LocalDateTime.now().toString());
        log.debug(message);
        MDC.clear();
    }
    
    // Convenience with auto traceId
    public static void logInfo(String userId, String serviceName, String message) {
        logInfo(UUID.randomUUID().toString(), userId, serviceName, message);
    }
    
    public static void logError(String userId, String serviceName, String message, Throwable ex) {
        logError(UUID.randomUUID().toString(), userId, serviceName, message, ex);
    }
    
    public static void logDebug(String userId, String serviceName, String message) {
        logDebug(UUID.randomUUID().toString(), userId, serviceName, message);
    }
}

