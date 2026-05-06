package com.library.common.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumTest {

    @Test
    void userRoleValues() {
        assertEquals(5, UserRole.values().length);
        assertEquals(UserRole.ADMIN, UserRole.valueOf("ADMIN"));
        assertThrows(IllegalArgumentException.class, () -> UserRole.valueOf("INVALID"));
    }

    @Test
    void ticketStatusValues() {
        TicketStatus[] values = TicketStatus.values();
        assertEquals(4, values.length);
        assertEquals(TicketStatus.OPEN, TicketStatus.valueOf("OPEN"));
        assertThrows(IllegalArgumentException.class, () -> TicketStatus.valueOf("INVALID"));
    }

    @Test
    void difficultyLevelValues() {
        DifficultyLevel[] values = DifficultyLevel.values();
        assertEquals(4, values.length);
        assertEquals(DifficultyLevel.EASY, DifficultyLevel.valueOf("EASY"));
        assertThrows(IllegalArgumentException.class, () -> DifficultyLevel.valueOf("INVALID"));
    }

    @Test
    void notificationTypeValues() {
        NotificationType[] values = NotificationType.values();
        assertEquals(4, values.length);
        assertEquals(NotificationType.EMAIL, NotificationType.valueOf("EMAIL"));
        assertThrows(IllegalArgumentException.class, () -> NotificationType.valueOf("INVALID"));
    }

    @Test
    void rewardTypeValues() {
        RewardType[] values = RewardType.values();
        assertEquals(3, values.length);
        assertEquals(RewardType.POINTS, RewardType.valueOf("POINTS"));
        assertThrows(IllegalArgumentException.class, () -> RewardType.valueOf("INVALID"));
    }
}
