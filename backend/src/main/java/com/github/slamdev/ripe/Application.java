package com.github.slamdev.ripe;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import static org.springframework.boot.SpringApplication.run;
import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

@SuppressWarnings("PMD.UseUtilityClass")
@SpringBootApplication
@EnableHypermediaSupport(type = HAL)
@EnableAsync
public class Application {

    @SuppressWarnings("PMD.UseVarargs")
    public static void main(String[] args) {
        run(Application.class, args);
    }
}
