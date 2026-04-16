package com.library.common.events;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Event for reward issuance.
 * Extends BaseEvent for common fields.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RewardEvent extends BaseEvent {
    
    private String userId;
    private int points;
    private String badge; // Badge name or ID
}

