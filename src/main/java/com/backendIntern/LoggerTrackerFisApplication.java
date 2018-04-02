package com.backendIntern;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@EnableBatchProcessing
public class LoggerTrackerFisApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerTrackerFisApplication.class, args);
	}
}
