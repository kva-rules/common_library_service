package com.library.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

import lombok.Value;

@Value
public class BaseEntity {

    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

