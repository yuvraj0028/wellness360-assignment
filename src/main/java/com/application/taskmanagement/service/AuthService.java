package com.application.taskmanagement.service;

import com.application.taskmanagement.dao.AuthDAO;
import com.application.taskmanagement.dto.UserDTO;
import com.application.taskmanagement.model.User;
import com.application.taskmanagement.util.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * AuthService handles the authentication logic for the application.
 * It provides methods for user registration (sign-up) and login by interacting with the data access layer (AuthDAO).
 * This service also implements the UserDetailsService interface to load user details by username.
 */
@Slf4j
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AuthDAO authDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authDAO.getUser(username.trim());
        if(Objects.isNull(user)) {
            throw new RuntimeException("User does not exist");
        }
        return user;
    }

    /**
     * Registers a new user by calling the DAO layer to sign up the user.
     * It returns a response containing the generated JWT token upon successful sign-up.
     *
     * @param userDTO contains user details such as email and password for sign-up
     * @return a ResponseEntity containing the response status and the JWT token if the sign-up is successful, or an error message if it fails
     */
    public ResponseEntity<Map<String, Object>> signUp(UserDTO userDTO){
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;
        try {
            String response = authDAO.signUp(userDTO);
            responseData = TaskUtil.responseJson(response, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.OK);
        } catch (RuntimeException e){
            log.error("Error creating user", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error creating user", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * Authenticates a user by verifying their credentials and generating a JWT token.
     * It returns a response containing the JWT token if login is successful, or an error message if authentication fails.
     *
     * @param userDTO contains user login details such as email and password
     * @return a ResponseEntity containing the response status and the JWT token if login is successful, or an error message if authentication fails
     */
    public ResponseEntity<Map<String, Object>> login(UserDTO userDTO){
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;
        try {
            String response = authDAO.login(userDTO);
            responseData = TaskUtil.responseJson(response, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.OK);
        } catch (RuntimeException e){
            log.error("Error logging in user", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error logging in user", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
