package com.ecommerce.project.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT Bearer Token");

        SecurityRequirement bearerRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce REST API")
                        .version("1.0.0")
                        .description("Production-style e-commerce REST API built with Spring Boot. " +
                                "Provides authentication, product management, shopping carts, " +
                                "orders, payments, image uploads and role-based authorization.")
                        .license(new License().name("Apache 2.0").url("http://fahimshaik.com"))
                        .contact(new Contact()
                                .name("Fahim Shaik")
                                .email("fahimshaik656@gmail.com")
                                .url("https://github.com/fahimshaik36")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("http://fahimshaik.com"))
                .tags(List.of(
                        new Tag()
                                .name("Authentication APIs")
                                .description("APIs for user authentication and account management"),
                        new Tag()
                                .name("Category APIs")
                                .description("APIs for managing categories"),
                        new Tag()
                                .name("Product APIs")
                                .description("APIs for managing products"),
                        new Tag()
                                .name("Cart APIs")
                                .description("APIs for managing shopping carts"),
                        new Tag()
                                .name("Address APIs")
                                .description("APIs for managing user addresses"),
                        new Tag()
                                .name("Order APIs")
                                .description("APIs for placing and managing orders")
                ))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", bearerScheme))
                .addSecurityItem(bearerRequirement);
    }
}