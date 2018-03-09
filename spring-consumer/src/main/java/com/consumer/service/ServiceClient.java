package com.consumer.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.model.Topic;

@Component
public class ServiceClient implements CommandLineRunner{

	public static void callRestService(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		Topic topic = restTemplate.getForObject("http://localhost:8080/topics/1", Topic.class);
		
		System.out.println("Topic is:"+ topic.getName());
		
	}

	@Override
	public void run(String... args) throws Exception {
		callRestService();
	}
	
	
}
