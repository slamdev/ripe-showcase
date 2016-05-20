package com.github.slamdev.ripe;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SuppressWarnings("PMD.UseUtilityClass")
@SpringBootApplication
public class Application {

    @SuppressWarnings("PMD.UseVarargs")
    public static void main(String[] args) {
        run(Application.class, args);
    }
}
