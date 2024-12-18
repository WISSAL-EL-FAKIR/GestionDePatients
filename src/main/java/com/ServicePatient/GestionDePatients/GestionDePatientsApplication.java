package com.ServicePatient.GestionDePatients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.ServicePatient.GestionDePatients.entities")
@EnableJpaRepositories(basePackages = "com.ServicePatient.GestionDePatients.repository")
public class GestionDePatientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDePatientsApplication.class, args);
	}

}
