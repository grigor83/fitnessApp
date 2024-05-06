package com.ip.fitnessApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FitnessAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessAppApplication.class, args);
	}

}
