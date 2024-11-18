package com.application.taskmanagement.dao;

import com.application.taskmanagement.dto.UserDTO;
import com.application.taskmanagement.model.User;

/**
 * AuthDAO is the Data Access Object (DAO) interface for handling authentication-related data operations.
 * It provides methods for user registration, login, and retrieving user details.
 */
public interface AuthDAO {
    /**
     * Registers a new user by saving their information into the database.
     *
     * @param userDTO the user data transfer object containing the user's registration information
     * @return a string message indicating the result of the registration (e.g., success or failure)
     */
    public String signUp(UserDTO userDTO);

    /**
     * Authenticates a user by validating their credentials and generating an authentication token (e.g., JWT).
     *
     * @param userDTO the user data transfer object containing the user's login credentials (email and password)
     * @return a string message indicating the result of the login (e.g., success or failure)
     */
    public String login(UserDTO userDTO);

    /**
     * Retrieves a user by their email address from the database.
     *
     * @param email the email address of the user to be retrieved
     * @return the User object containing the user's details, or null if no user is found with the provided email
     */
    public User getUser(String email);
}
