package com.library.common.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Validator;

/**
 * Optional configuration for common library.
 * Imported by consuming apps if needed.
 */
@Configuration
public class CommonLibraryConfig {

    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}

