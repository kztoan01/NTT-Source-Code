package com.NTTT.PersonalInfoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PersonalInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalInfoServiceApplication.class, args);
	}

}
