package com.application.taskmanagement.controller;

import com.application.taskmanagement.dto.UserDTO;
import com.application.taskmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * AuthController handles authentication-related endpoints such as login and signup.
 * It delegates the actual business logic to the AuthService.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    /**
     * Handles the login request. It takes a UserDTO object, which contains user credentials,
     * and returns a ResponseEntity containing a map with authentication details (e.g., JWT token).
     *
     * @param userDTO the user credentials (username/email and password) from the request body
     * @return a ResponseEntity containing the login response, typically a JWT token
     */
    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json",name = "login")
    ResponseEntity<Map<String, Object>> login(@RequestBody(required = true) UserDTO userDTO) {return authService.login(userDTO);}

    /**
     * Handles the signup request. It takes a UserDTO object, which contains user registration data,
     * and returns a ResponseEntity containing a map with details of the registration response.
     *
     * @param userDTO the user registration data (such as email, password, etc.) from the request body
     * @return a ResponseEntity containing the signup response (e.g., success message or user data)
     */
    @PostMapping(value = "/signup", produces = "application/json", consumes = "application/json",name = "signup")
    ResponseEntity<Map<String, Object>> signUp(@RequestBody(required = true) UserDTO userDTO) {return authService.signUp(userDTO);}
}
