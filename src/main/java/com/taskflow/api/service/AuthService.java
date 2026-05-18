package com.taskflow.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskflow.api.model.User;
import com.taskflow.api.repository.UserRepository;



@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	// Sign up
	public User	registerUser(User user) {
		if (userRepository.findByUsername(user.getEmail()).isPresent()){
			throw new RuntimeException("Username is already taken!");
		}
		return userRepository.save(user);
	}

	// login
	public User	loginUser(String userName, String passwd) {
		Optional<User>	userOpt = userRepository.findByUsername(userName);

		if (userOpt.isPresent()) {
			User user = userOpt.get();

			if (user.getPasswd().equals(passwd))
				return user;
		}
		throw new RuntimeException("Invalid username or password!");
	}
}
