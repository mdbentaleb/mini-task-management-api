package com.taskflow.api.controller;

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
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private AuthService	authService;

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