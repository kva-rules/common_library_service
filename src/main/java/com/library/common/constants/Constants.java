package com.library.common.constants;

/**
 * Application-wide constants including Kafka topic names.
 */
public final class Constants {

    private Constants() {} // Prevent instantiation

    // Kafka Topics
    public static final String TOPIC_TICKET_CREATED = "ticket.created";
    public static final String TOPIC_SOLUTION_APPROVED = "solution.approved";
    public static final String TOPIC_REWARD_ADDED = "reward.added";

    // Additional Kafka Topics
    public static final String TOPIC_TICKET_UPDATED = "ticket.updated";
    public static final String TOPIC_TICKET_CLOSED = "ticket.closed";
    public static final String TOPIC_SOLUTION_SUBMITTED = "solution.submitted";
    public static final String TOPIC_SOLUTION_REJECTED = "solution.rejected";
    public static final String TOPIC_USER_REGISTERED = "user.registered";
    public static final String TOPIC_NOTIFICATION_SENT = "notification.sent";

    // API Paths
    public static final String API_V1 = "/api/v1";

    // Headers
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_BEARER_PREFIX = "Bearer ";
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String HEADER_USER_ROLE = "X-User-Role";
}
