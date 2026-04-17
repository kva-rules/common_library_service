package com.library.common.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolutionApprovedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long solutionId;
    private Long ticketId;
    private List<Long> contributorIds;
    private String difficulty;
    private String solutionText;
}
