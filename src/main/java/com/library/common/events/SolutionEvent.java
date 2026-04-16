package com.library.common.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Event for solution approval/contribution.
 * Extends BaseEvent for common fields. Use BaseEvent.builder() for chaining.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionEvent extends BaseEvent {
    
    private UUID solutionId;
    private UUID ticketId;
    private String contributorId; // User ID
    private boolean approved;
}

