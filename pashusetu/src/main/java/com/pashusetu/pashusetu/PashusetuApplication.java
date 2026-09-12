package com.pashusetu.pashusetu;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class PashusetuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PashusetuApplication.class, args);
	}

}
