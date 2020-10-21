package com.ping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PingStatusApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingStatusApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PingStatusApplication.class, args);
		LOGGER.info("Starting the PingStatusApplication");
	}

}
