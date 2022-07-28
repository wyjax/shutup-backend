package com.wyjax.shutup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShutupApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShutupApplication.class, args);
	}

}
