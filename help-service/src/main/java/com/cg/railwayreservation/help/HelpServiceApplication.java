package com.cg.railwayreservation.help;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.*")
public class HelpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpServiceApplication.class, args);
	}

}
