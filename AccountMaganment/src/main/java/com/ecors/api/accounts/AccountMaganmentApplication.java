package com.ecors.api.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountMaganmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountMaganmentApplication.class, args);
	}

}
