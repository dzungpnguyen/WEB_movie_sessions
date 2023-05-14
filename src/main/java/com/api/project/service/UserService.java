package com.api.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.project.model.UserModel;
import com.api.project.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public UserModel getUserByUsername(String username) {
		Optional<UserModel> user = userRepository.findByUsername(username);
    	if (user.isEmpty()) {
    		throw new EntityNotFoundException("No user found with the username: " + username);
    	}
    	return user.orElse(null);
	}
}
