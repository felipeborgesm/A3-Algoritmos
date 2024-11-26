package com.a3_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class A3BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(A3BackendApplication.class, args);
	}

}
