package com.library.common.events;

import com.library.common.enums.TicketStatus;
import com.library.common.enums.NotificationType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void baseEventStructure() throws Exception {
        BaseEvent event = new BaseEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventType("TEST");
        event.setTimestamp(null); // Skip timestamp for serialization test
        event.setSourceService("test-service");
        event.setPayload("test-payload");

        assertNotNull(event.getEventId());
        assertEquals("TEST", event.getEventType());
        assertEquals("test-service", event.getSourceService());
        assertEquals("test-payload", event.getPayload());

        // JSON serialization without timestamp
        String json = mapper.writeValueAsString(event);
        assertTrue(json.contains("\"eventType\":\"TEST\""));
    }

    @Test
    void ticketEventPayload() throws Exception {
        TicketEvent event = new TicketEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventType("TICKET_UPDATED");
        event.setTimestamp(null);
        event.setSourceService("ticket-service");
        event.setTicketId(UUID.randomUUID());
        event.setTitle("Test Ticket");
        event.setStatus(TicketStatus.OPEN);
        event.setAssignedTo("user123");

        String json = mapper.writeValueAsString(event);
        ObjectNode node = (ObjectNode) mapper.readTree(json);
        assertEquals("TICKET_UPDATED", node.get("eventType").asText());
        assertEquals("OPEN", node.get("status").asText());
        assertEquals("user123", node.get("assignedTo").asText());
        assertTrue(node.has("ticketId"));
    }

    @Test
    void solutionEventPayload() throws Exception {
        SolutionEvent event = new SolutionEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventType("SOLUTION_APPROVED");
        event.setTimestamp(null);
        event.setSourceService("solution-service");
        event.setSolutionId(UUID.randomUUID());
        event.setTicketId(UUID.randomUUID());
        event.setContributorId("eng456");
        event.setApproved(true);

        String json = mapper.writeValueAsString(event);
        ObjectNode node = (ObjectNode) mapper.readTree(json);
        assertTrue(node.get("approved").asBoolean());
        assertEquals("SOLUTION_APPROVED", node.get("eventType").asText());
        assertTrue(node.has("solutionId"));
    }

    @Test
    void rewardEventPayload() throws Exception {
        RewardEvent event = new RewardEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventType("REWARD_ISSUED");
        event.setTimestamp(null);
        event.setSourceService("reward-service");
        event.setUserId("user123");
        event.setPoints(100);
        event.setBadge("Gold Badge");

        String json = mapper.writeValueAsString(event);
        ObjectNode node = (ObjectNode) mapper.readTree(json);
        assertEquals(100, node.get("points").asInt());
        assertEquals("Gold Badge", node.get("badge").asText());
    }

    @Test
    void notificationEventPayload() throws Exception {
        NotificationEvent event = new NotificationEvent();
        event.setEventId(UUID.randomUUID());
        event.setEventType("NOTIFICATION_SENT");
        event.setTimestamp(null);
        event.setSourceService("notification-service");
        event.setUserId("user123");
        event.setTitle("New Ticket");
        event.setMessage("Ticket assigned to you");
        event.setType(NotificationType.EMAIL);

        String json = mapper.writeValueAsString(event);
        ObjectNode node = (ObjectNode) mapper.readTree(json);
        assertEquals("EMAIL", node.get("type").asText());
        assertEquals("New Ticket", node.get("title").asText());
    }
}

