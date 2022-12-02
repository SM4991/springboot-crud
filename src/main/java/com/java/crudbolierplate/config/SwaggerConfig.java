package com.java.crudbolierplate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.java.crudbolierplate"))
                .apis(RequestHandlerSelectors.basePackage("com.java.crudbolierplate.controller.api"))
//                .paths(regex("/product.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot CRUD Boilerplate",
                "A Spring Boot application boilerplate that provides a quick and easy setup for creating RESTful APIs with a database backend.",
                "1.0",
                "Terms of service",
                "Surabhi Mahawar (Email: mahawarsurabhi@gmail.com)",
                null,
                null);
        return apiInfo;
    }

}