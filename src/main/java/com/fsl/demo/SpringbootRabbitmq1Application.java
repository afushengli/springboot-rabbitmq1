package com.fsl.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootRabbitmq1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmq1Application.class, args);
    }

}
