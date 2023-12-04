package com.example;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.Entity;

@OpenAPIDefinition(
    info = @Info(
            title = "micronautApp",
            version = "0.0"
    )
)
@Introspected(packages="com.example.micronaut.model.beans", includedAnnotations= Entity.class)
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}