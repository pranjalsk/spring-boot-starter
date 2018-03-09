package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.consumer.service"})
public class NetClient {

	public static void main(String[] args) {
		
		SpringApplication.run(NetClient.class, args);
		
	}
	
}
