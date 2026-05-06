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
public class SolutionRejectedEvent implements Serializable {
    private static final long serialVersionUID = 1L;

    private String solutionId;
    private String ticketId;
    private String createdBy;
    private String rejectionReason;
    private String solutionTitle;
}
