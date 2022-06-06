package com.atlas.skull;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

@SpringBootApplication
@Async
//@OpenAPIDefinition
public class SkullApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkullApplication.class, args);
    }

}
