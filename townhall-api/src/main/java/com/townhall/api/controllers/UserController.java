package com.townhall.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.townhall.api.model.User;
import com.townhall.api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
//	@Autowired
//	UserService userService;
	
//	@PostMapping
//	public void registerUser() {
//		userService.
//	}
	
    @GetMapping(value="welcome", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> users() {
		String welcomeUsers =  "Welcome Users";
		return ResponseEntity.ok().body(welcomeUsers);
	}

    @GetMapping(value="welcome1", produces= {"application/json"})
   	public ResponseEntity<User> users1() {
   		String welcomeUsers =  "Welcome Users1";
   		User u = new User();
   		//u.setUserName(welcomeUsers);
   		ResponseEntity<User> apiResponseEntity = null;
   		apiResponseEntity = new ResponseEntity<>(u, HttpStatus.OK);
   		//return ResponseEntity.ok().body(welcomeUsers);
   		return apiResponseEntity;
   	}
}
