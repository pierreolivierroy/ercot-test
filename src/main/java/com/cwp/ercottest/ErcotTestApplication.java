package com.cwp.ercottest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class ErcotTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErcotTestApplication.class, args);
	}
}
