package com.library.common.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI commonLibraryOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        Server server = new Server().description("Default Server URL").url("http://localhost:8080");
        Contact contact = new Contact().name("Common Library Team").email("team@example.com");
        Info info = new Info()
                .title("Common Library API")
                .version("1.0")
                .description("Reusable common library APIs for microservices")
                .contact(contact);
        return new OpenAPI().info(info);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .pathsToMatch("/public/**")
                .build();
    }
}

