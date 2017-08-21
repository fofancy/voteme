package com.fofancy;

import com.fofancy.auth.data.AuthDbConfigurationProps;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//@EnableConfigurationProperties(AuthDbConfigurationProps.class)
@ComponentScan("com.fofancy")
public class VotemeAuthApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(VotemeAuthApplication.class, args);
//	}
}
