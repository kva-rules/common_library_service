package com.library.common.util;

import com.library.common.enums.DifficultyLevel;
import com.library.common.enums.UserRole;
import com.library.common.exception.AccessDeniedException;
import com.library.common.exception.InvalidRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void constantsTest() {
        // AppConstants
        assertEquals(20, com.library.common.constants.AppConstants.DEFAULT_PAGE_SIZE);
        assertEquals(100, com.library.common.constants.AppConstants.MAX_PAGE_SIZE);
        assertEquals("admin@system.com", com.library.common.constants.AppConstants.SYSTEM_ADMIN_EMAIL);
        assertEquals("X-Trace-Id", com.library.common.constants.AppConstants.TRACE_ID_HEADER);
        assertEquals("anonymousUser", com.library.common.constants.AppConstants.ANONYMOUS_USER);
        
        // KafkaTopics
        assertEquals("ticket-events", com.library.common.constants.KafkaTopics.TICKET_EVENTS);
        assertEquals("solution-events", com.library.common.constants.KafkaTopics.SOLUTION_EVENTS);
        assertEquals("reward-events", com.library.common.constants.KafkaTopics.REWARD_EVENTS);
        assertEquals("notification-events", com.library.common.constants.KafkaTopics.NOTIFICATION_EVENTS);
        assertEquals("dlq-ticket-events", com.library.common.constants.KafkaTopics.DLQ_TICKET_EVENTS);
    }

    @Test
    void validationUtil() {
        ValidationUtil.validateUUID("123e4567-e89b-12d3-a456-426614174000");
        assertThrows(InvalidRequestException.class, () -> ValidationUtil.validateUUID("invalid-uuid"));
        
        ValidationUtil.validateEmail("test@example.com");
        assertThrows(InvalidRequestException.class, () -> ValidationUtil.validateEmail("invalid-email"));
        
        ValidationUtil.validatePagination(0, 10);
        ValidationUtil.validatePagination(5, 50);
        assertThrows(InvalidRequestException.class, () -> ValidationUtil.validatePagination(-1, 10));
        assertThrows(InvalidRequestException.class, () -> ValidationUtil.validatePagination(0, 0));
        assertThrows(InvalidRequestException.class, () -> ValidationUtil.validatePagination(0, 101));
        
        ValidationUtil.validateDifficultyLevel("EASY");
        assertThrows(InvalidRequestException.class, () -> ValidationUtil.validateDifficultyLevel("INVALID"));
    }

    @Test
    void dateUtil() {
        LocalDateTime now = DateUtil.getCurrentTimestamp();
        assertNotNull(now);
        
        String formatted = DateUtil.formatDate(now);
        assertNotNull(formatted);
        assertTrue(formatted.contains("T"));
        
        assertThrows(InvalidRequestException.class, () -> DateUtil.parseDate("invalid"));
        LocalDateTime parsed = DateUtil.parseDate("2024-01-01T12:00:00");
        assertEquals(12, parsed.getHour());
    }

    @Test
    void traceUtil() {
        String traceId = TraceUtil.generateTraceId();
        assertNotNull(traceId);
        assertEquals(32, traceId.length()); // UUID without -
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(TraceUtil.TRACE_ID_HEADER, "custom-trace-123");
        String extracted = TraceUtil.getTraceId(headers);
        assertEquals("custom-trace-123", extracted);
        
        HttpHeaders newHeaders = TraceUtil.generateAndAddTraceId(new HttpHeaders());
        assertTrue(newHeaders.containsKey(TraceUtil.TRACE_ID_HEADER));
    }

    @Test
    void securityUtilWithMockAuth() {
        // Mock authenticated user
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username("user123")
            .password("pass")
            .roles("ADMIN")
            .build();
        Authentication auth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, null, 
            List.of(new SimpleGrantedAuthority("ADMIN")));
        
        SecurityContext context = org.springframework.security.core.context.SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        
        assertEquals("user123", SecurityUtil.getCurrentUserId());
        assertTrue(SecurityUtil.getCurrentUserRoles().contains(UserRole.ADMIN));
        assertTrue(SecurityUtil.hasRole(UserRole.ADMIN));
        
        SecurityUtil.validateRoleAccess(UserRole.ADMIN);
        assertThrows(AccessDeniedException.class, () -> SecurityUtil.validateRoleAccess(UserRole.ENGINEER));
    }
}

