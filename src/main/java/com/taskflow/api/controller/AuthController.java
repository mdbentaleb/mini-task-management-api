package com.taskflow.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskflow.api.model.User;
import com.taskflow.api.service.AuthService;



@RestController
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private AuthService	authService;

	@Operation(summary = "Register a new user", description = "Creates a new user account in the system")
	@PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
			User registeredUser = authService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        }
        catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
	

	@Operation(summary = "User login", description = "Authenticates user and returns user data with UUID")
	@PostMapping("/signin")
	public ResponseEntity<?> signing(@RequestBody User loginRequest) {
		try {
			User user = authService.loginUser(loginRequest.getUsername(), loginRequest.getPasswd());
            return ResponseEntity.ok(user);
		} catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
	}
}