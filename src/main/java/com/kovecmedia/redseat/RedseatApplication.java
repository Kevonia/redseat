package com.kovecmedia.redseat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class RedseatApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(RedseatApplication.class, args);
	    
	
	}

}
