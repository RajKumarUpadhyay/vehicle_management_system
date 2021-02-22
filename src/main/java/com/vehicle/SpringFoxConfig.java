package com.vehicle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vehicle.controller"))
                .paths(PathSelectors.ant("/vehicle-management-service/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Vehicle Management Service",
                "Vehicle Management Service API to handle all defined operation for Cars and Dealers.",
                "1.0",
                "Terms of service",
                new Contact("Raj K Upadhyay", "www.heycar.com", "raj_upadhyay@hotmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}