package com.gestionEcole.gestionEcole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionEcoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionEcoleApplication.class, args);
	}

}
