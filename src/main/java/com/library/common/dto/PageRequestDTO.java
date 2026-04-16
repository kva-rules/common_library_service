package com.library.common.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pagination request DTO with validation.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {

    @NotNull(message = "Page number is required")
    @Min(value = 0, message = "Page must be >= 0")
    private Integer page;

    @NotNull(message = "Page size is required")
    @Min(value = 1, message = "Page size must be >= 1")
    @Max(value = 100, message = "Page size must be <= 100")
    private Integer size;

    @NotBlank(message = "Sort by field is required")
    private String sortBy;

    @NotNull(message = "Sort direction is required")
    private SortDirection sortDirection;
}

enum SortDirection {
    ASC, DESC
}
