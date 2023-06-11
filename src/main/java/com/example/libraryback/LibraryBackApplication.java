package com.example.libraryback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties({FileStorageProperties.class})

public class LibraryBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBackApplication.class, args);
	}

}
