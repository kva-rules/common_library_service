package com.library.common.util;

import org.springframework.http.HttpHeaders;

import java.util.UUID;

/**
 * Utility for distributed tracing with X-Trace-Id header.
 */
public class TraceUtil {
    
    public static final String TRACE_ID_HEADER = "X-Trace-Id";
    
    /**
     * Generate new unique trace ID.
     */
    public static String generateTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    /**
     * Extract trace ID from headers, generate new if absent.
     */
    public static String getTraceId(HttpHeaders headers) {
        if (headers != null && headers.containsKey(TRACE_ID_HEADER)) {
            return headers.getFirst(TRACE_ID_HEADER);
        }
        return generateTraceId();
    }
    
    /**
     * Add trace ID to headers.
     */
    public static HttpHeaders addTraceId(HttpHeaders headers, String traceId) {
        if (headers == null) {
            headers = new HttpHeaders();
        }
        headers.add(TRACE_ID_HEADER, traceId);
        return headers;
    }
    
    /**
     * Generate and add new trace ID to headers.
     */
    public static HttpHeaders generateAndAddTraceId(HttpHeaders headers) {
        return addTraceId(headers, generateTraceId());
    }
}

