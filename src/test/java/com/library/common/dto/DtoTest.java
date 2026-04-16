package com.library.common.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DtoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void pageRequestDtoValidation() {
        LocalValidatorFactoryBean validatorFactory = new LocalValidatorFactoryBean();
        validatorFactory.afterPropertiesSet();
        Validator validator = validatorFactory.getValidator();
        PageRequestDTO valid = PageRequestDTO.builder()
                .page(0)
                .size(10)
                .sortBy("id")
                .sortDirection(SortDirection.ASC)
                .build();
        
        Set<ConstraintViolation<PageRequestDTO>> violations = validator.validate(valid);
        assertTrue(violations.isEmpty());
        
        PageRequestDTO invalidPage = PageRequestDTO.builder()
                .page(-1)
                .size(10)
                .sortBy("id")
                .sortDirection(SortDirection.ASC)
                .build();
        violations = validator.validate(invalidPage);
        assertFalse(violations.isEmpty());
    }

    @Test
    void pageResponseDtoSerialization() throws Exception {
        PageResponseDTO<String> response = PageResponseDTO.<String>builder()
                .totalElements(100)
                .totalPages(10)
                .currentPage(0)
                .data(List.of("item1", "item2"))
                .build();

        String json = objectMapper.writeValueAsString(response);
        assertThat(json).contains("totalElements\":100");
        assertThat(json).contains("\"data\":[");
        
        // Deserialization
        PageResponseDTO<String> deserialized = objectMapper.readValue(json, PageResponseDTO.class);
        assertEquals(100, deserialized.getTotalElements());
    }

    @Test
    void apiResponseFields() {
        ApiResponse<String> response = ApiResponse.<String>builder()
                .status(200)
                .message("Success")
                .data("test-data")
                .build();

        assertEquals(200, response.getStatus());
        assertEquals("Success", response.getMessage());
        assertEquals("test-data", response.getData());
    }

    @Test
    void errorResponseFields() {
        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .error("Bad Request")
                .message("Invalid input")
                .build();

        assertEquals(400, error.getStatus());
        assertEquals("Bad Request", error.getError());
        assertEquals("Invalid input", error.getMessage());
    }
}

