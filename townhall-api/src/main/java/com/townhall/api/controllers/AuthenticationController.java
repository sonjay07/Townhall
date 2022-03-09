package com.townhall.api.controllers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.townhall.api.dto.JWTAuthResponse;
import com.townhall.api.dto.LoginDto;
import com.townhall.api.dto.SignUpDto;
import com.townhall.api.jwt.JwtTokenProvider;
import com.townhall.api.model.Role;
import com.townhall.api.model.User;
import com.townhall.api.repositories.RoleRepository;
import com.townhall.api.repositories.UserRepository;
import com.townhall.api.services.UserService;

@RestController

@RequestMapping("/api/auth")
public class AuthenticationController {
	
//	@Autowired
//	UserService topicsService;
//	
//	//@GetMapping(path="hello",  produces= {"application/xml"})       // returns xml or json
//	@GetMapping(path="hello",  produces= {"application/json"})       // returns xml or json
//	//@GetMapping
//	///@ResponseBody no need to use @ResponseBody, if in the class annotation it is using @RestController
//	public List<String> helloFromApi() {
//		List<String> list = new ArrayList<String>();
//		String hello = "hello";
//		return list;
//	}
//	
//	//@PostMapping(path="test", consumes= {"application/json"})                   // the same
//	@PostMapping(path="test", consumes= {MediaType.APPLICATION_JSON_VALUE})       // the same
//	public String add(@RequestBody String name) { // use requestbody  if post mapping, converts json to java object e.g. string
//		return "";
//	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	   @CrossOrigin
	   @PostMapping("/signin")
	    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // get token form tokenProvider
	        String token = tokenProvider.generateToken(authentication);
	        
	        List<String> dummyRoles = new ArrayList<String>();
	        dummyRoles.add("ROLE_MANAGER");
	        dummyRoles.add("ROLE_ADMIN");
	        dummyRoles.add("ROLE_USER");



	        return ResponseEntity.ok(new JWTAuthResponse(token, dummyRoles));
	    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        System.out.println("ROLE NYA...... " + roles.getName());
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
