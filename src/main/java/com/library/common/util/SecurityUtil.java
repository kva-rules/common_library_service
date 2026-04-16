package com.library.common.util;

import com.library.common.enums.UserRole;
import com.library.common.exception.AccessDeniedException;
import com.library.common.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Security utilities for user context and role validation.
 */
public class SecurityUtil {
    
    /**
     * Get current user ID from SecurityContext (sub/jwt claim).
     * Throws UnauthorizedException if not authenticated.
     */
    public static String getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() == "anonymousUser") {
            throw new UnauthorizedException("User not authenticated");
        }
        // Assume principal is UserDetails or JWT with 'sub'
        return Optional.ofNullable(auth.getName())
                .filter(id -> !id.equals("anonymousUser"))
                .orElseThrow(() -> new UnauthorizedException("User ID not found"));
    }
    
    /**
     * Get current user roles from authorities.
     */
    public static List<UserRole> getCurrentUserRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getAuthorities() == null) {
            return List.of();
        }
        return auth.getAuthorities().stream()
                .map(granted -> UserRole.valueOf(granted.getAuthority())) // Assume ROLE_ADMIN -> ADMIN
                .collect(Collectors.toList());
    }
    
    /**
     * Validate if current user has required roles.
     * Throws AccessDeniedException if not.
     */
    public static void validateRoleAccess(UserRole... requiredRoles) {
        List<UserRole> userRoles = getCurrentUserRoles();
        boolean hasAccess = List.of(requiredRoles).stream()
                .anyMatch(userRoles::contains);
        if (!hasAccess) {
            throw new AccessDeniedException("Insufficient roles. Required: " + List.of(requiredRoles));
        }
    }
    
    /**
     * Check if current user has required roles (no throw).
     */
    public static boolean hasRole(UserRole role) {
        return getCurrentUserRoles().contains(role);
    }
}

