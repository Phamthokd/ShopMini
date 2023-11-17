package com.thuongmaidientu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.thuongmaidientu.service.StorageService;

@SpringBootApplication
public class ShopMiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopMiniApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
