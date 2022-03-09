package com.townhall.api;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.townhall.api.dto.CategoryDto;

public class TestContextClass {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TownhallApiApplication.class, args);
		
		
		// creating the object via application context
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(CategoryDto.class);
		CategoryDto cto = ctx.getBean(CategoryDto.class);
		cto.code();
		
		
	}

}
