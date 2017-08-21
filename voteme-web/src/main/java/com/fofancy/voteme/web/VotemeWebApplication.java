package com.fofancy.voteme.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.fofancy")
public class VotemeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotemeWebApplication.class, args);
	}
}
