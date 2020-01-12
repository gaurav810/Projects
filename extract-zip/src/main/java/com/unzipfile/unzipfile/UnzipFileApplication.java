package com.unzipfile.unzipfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class UnzipFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnzipFileApplication.class, args);
	}
}
