package com.tests.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SessionApplication {

	private static Logger logger = LoggerFactory.getLogger(SessionApplication.class);

	public static void main(String[] args) {
		logger.info("Aplicacao Sendo Iniciada");
		SpringApplication.run(SessionApplication.class, args);
		logger.info("API Votos iniciada");

	}

}
