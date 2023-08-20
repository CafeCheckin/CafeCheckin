package com.example.CafeTour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CafeTourApplication {
	public static void main(String[] args) {
		SpringApplication.run(CafeTourApplication.class, args);
	}

}
