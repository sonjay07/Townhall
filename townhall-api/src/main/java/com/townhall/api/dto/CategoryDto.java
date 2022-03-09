package com.townhall.api.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CategoryDto {

	@Autowired
	TopicDto tdo;
	
	//@Bean
	public void code() {
		System.out.println("Hello World Jayson");
		tdo.compile();
	}
}
