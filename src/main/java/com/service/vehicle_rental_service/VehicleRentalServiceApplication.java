package com.service.vehicle_rental_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.service.vehicle_rental_service")
public class VehicleRentalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleRentalServiceApplication.class, args);
	}

}
