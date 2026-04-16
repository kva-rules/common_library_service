package com.library.common.events;

import com.library.common.enums.TicketStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Specific event for ticket lifecycle.
 * Extends BaseEvent for common fields. Use BaseEvent.builder() for chaining.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TicketEvent extends BaseEvent {
    
    private UUID ticketId;
    private String title;
    private TicketStatus status;
    private String assignedTo; // UserId or email
}

