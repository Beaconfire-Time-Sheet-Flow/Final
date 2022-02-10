package com.example.employeemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmployeeMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeMicroserviceApplication.class, args);
    }

}
