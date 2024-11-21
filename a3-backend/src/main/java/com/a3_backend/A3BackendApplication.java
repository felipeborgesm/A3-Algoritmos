package com.a3_backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition(
		info =
		@Info(
				title = "API Sistema Bancario",
				version = "2.0",
				description =
						"Essa API simula um sistema de estoque"))
public class A3BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(A3BackendApplication.class, args);
	}

}
