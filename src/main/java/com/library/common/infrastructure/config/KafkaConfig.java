package com.library.common.infrastructure.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KafkaPropertiesConfig.class)
public class KafkaConfig {
    // Standard serializer/deserializer configs are auto-configured by spring-boot-starter-kafka
    // when consumers/services are imported. Uses KafkaPropertiesConfig for app.kafka.*
}


