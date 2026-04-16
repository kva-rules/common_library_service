package com.library.common.events;

import com.library.common.enums.NotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Event for sending notifications.
 * Extends BaseEvent for common fields.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NotificationEvent extends BaseEvent {
    
    private String userId;
    private String title;
    private String message;
    private NotificationType type;
}

