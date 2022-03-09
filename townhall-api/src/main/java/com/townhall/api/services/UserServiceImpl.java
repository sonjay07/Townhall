package com.townhall.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.townhall.api.model.User;
import com.townhall.api.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository topicsRepo;

	@Override
	public void registerUser(User user) {
		topicsRepo.save(user);
	}
	
	public void getUseByUserName(String username) {
		topicsRepo.findByUsername(username);
	}
}
