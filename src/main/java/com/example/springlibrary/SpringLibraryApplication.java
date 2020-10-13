package com.example.springlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages="com.example.springlibrary")
//@ComponentScan({ "com.example.springlibrary.controller", "com.example.springlibrary,service.BookService" })

public class SpringLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLibraryApplication.class, args);
	}

}

