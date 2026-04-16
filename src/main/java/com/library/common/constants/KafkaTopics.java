package com.library.common.constants;

/**
 * Kafka topic names across system services.
 */
public final class KafkaTopics {
    
    private KafkaTopics() {} // Prevent instantiation
    
    // Events
    public static final String TICKET_EVENTS = "ticket-events";
    public static final String SOLUTION_EVENTS = "solution-events";
    public static final String REWARD_EVENTS = "reward-events";
    public static final String NOTIFICATION_EVENTS = "notification-events";
    
    // Notifications
    public static final String NOTIFICATIONS = "notifications";
    
    // User/Service
    public static final String USER_REGISTRATION = "user-registration";
    public static final String SERVICE_AUDIT = "service-audit";
    
    // Dead letter queues
    public static final String DLQ_TICKET_EVENTS = "dlq-ticket-events";
    public static final String DLQ_NOTIFICATION_EVENTS = "dlq-notification-events";
}

