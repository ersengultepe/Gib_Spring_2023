package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Gib {

    public static void main(String[] args) {
        SpringApplication.run(Gib.class, args);
    }

}
