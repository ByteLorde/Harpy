package com.harpy.harpyclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class HarpyClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarpyClientApplication.class, args);
	}

}
