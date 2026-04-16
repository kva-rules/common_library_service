package com.library.common.events;

import com.library.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base class for domain events.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEvent {
    
    private UUID eventId;
    private String eventType;
    private LocalDateTime timestamp;
    private String sourceService;
    private Object payload; // Generic payload, e.g. JSON/String/Entity
}

