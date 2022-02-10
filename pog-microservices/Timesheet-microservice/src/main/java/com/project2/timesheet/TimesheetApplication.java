package com.project2.timesheet;

import lombok.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.sql.DataSource;

//@EnableMongoRepositories('repository')
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
//@EnableDiscoveryClient
public class TimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetApplication.class, args);
	}

}
