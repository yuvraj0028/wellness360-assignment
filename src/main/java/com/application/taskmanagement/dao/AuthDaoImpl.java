package com.application.taskmanagement.dao;

import com.application.taskmanagement.db.DB;
import com.application.taskmanagement.dto.UserDTO;
import com.application.taskmanagement.model.User;
import com.application.taskmanagement.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * AuthDaoImpl is the implementation of the AuthDAO interface. It handles authentication operations,
 * including user registration (sign-up), user login, and user retrieval from the database.
 * This implementation also integrates JWT token generation and password encoding.
 */
@Service
public class AuthDaoImpl implements AuthDAO{
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String signUp(UserDTO userDTO) {
        // validating user
        validateUserFields(userDTO);
        User user = getUser(userDTO.getEmail().trim());

        // checking if user already exists
        if(Objects.nonNull(user)) {
            throw new RuntimeException("User already exists");
        }
        user = new User();
        user.setEmail(userDTO.getEmail().trim());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword().trim()));

        // generating token and saving user
        String token = jwtHelper.generateToken(user.getEmail().trim());
        DB.users.put(user.getEmail().trim(), user);

        return token;
    }

    @Override
    public String login(UserDTO userDTO) {
        // validating user
        validateUserFields(userDTO);
        User user = getUser(userDTO.getEmail().trim());

        // checking if user exists
        if(Objects.isNull(user)) {
            throw new RuntimeException("User does not exist");
        }
        // checking if password is correct
        if(!passwordEncoder.matches(userDTO.getPassword().trim(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtHelper.generateToken(user.getEmail().trim());
    }

    @Override
    public User getUser(String email) {
        if(DB.users.containsKey(email)) {
            return DB.users.get(email);
        }
        return null;
    }

    /**
     * Validates the fields in the userDTO (email and password).
     *
     * @param userDTO the user data transfer object to be validated
     * @throws RuntimeException if any required field is missing or invalid
     */
    private void validateUserFields(UserDTO userDTO) {
        if(userDTO == null) {
            throw new RuntimeException("User is null");
        }
        if(userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email is null");
        }
        if(userDTO.getPassword() == null || userDTO.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Password is null");
        }

        // validating email and password
        validateEmail(userDTO.getEmail().trim());
        validatePassword(userDTO.getPassword().trim());
    }

    /**
     * Validates the email format using a regular expression.
     *
     * @param email the email address to be validated
     * @throws RuntimeException if the email is not valid
     */
    private void validateEmail(String email){
        // checking if given string is email or not
        if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            throw new RuntimeException("Email is not valid");
        }
    }

    /**
     * Validates the password strength using a regular expression.
     * The password must be at least 8 characters long and include at least one digit,
     * one special character, one uppercase letter, and one lowercase letter.
     *
     * @param password the password to be validated
     * @throws RuntimeException if the password does not meet the required criteria
     */
    private void validatePassword(String password){
        // checking if the password is more than 8 characters and contains at least one special character, one digit,
        // one uppercase letter, and one lowercase letter
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            throw new RuntimeException("Password is not valid, must be more than 8 characters and contains at least one special character and one digit and one uppercase letter and one lowercase letter");
        }
    }
}
