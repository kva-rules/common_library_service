package com.library.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreatedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long ticketId;
    private String title;
    private Long assignedUserId;
    private Long creatorUserId;
    private String difficulty;
}
