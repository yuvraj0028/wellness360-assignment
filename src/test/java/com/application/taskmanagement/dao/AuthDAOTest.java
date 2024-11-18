package com.application.taskmanagement.dao;

import com.application.taskmanagement.db.DB;
import com.application.taskmanagement.dto.UserDTO;
import com.application.taskmanagement.model.User;
import com.application.taskmanagement.util.JwtHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for {@link AuthDaoImpl}.
 * These tests verify the behavior of signup and user retrieval methods
 * using mocked dependencies for database operations and utilities.
 */
@ExtendWith(MockitoExtension.class)
public class AuthDAOTest {
    @InjectMocks
    private AuthDaoImpl authDao;

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSignup(){
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            Mockito.lenient().when(passwordEncoder.encode(getMockUserSignup().getPassword())).thenReturn("Password@123");
            Mockito.lenient().when(jwtHelper.generateToken(getMockUserSignup().getPassword())).thenReturn("token");
            String token = authDao.signUp(getMockUserDTO());
            assertNull(token);
        }
    }

    @Test
    public void testGetUser(){
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            DB.users.put(getMockUserLogin().getEmail(), getMockUserLogin());
            User user = authDao.getUser(getMockUserLogin().getEmail());
            assertNotNull(user);
        }
    }

    // mock data for testing UserDTO
    private UserDTO getMockUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email@email.com");
        userDTO.setPassword("Password@123");
        return userDTO;
    }

    // mock data for testing User
    private User getMockUserSignup() {
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("Password@123");
        return user;
    }

    // mock data for testing UserDTO
    private User getMockUserLogin() {
        User user = new User();
        user.setEmail("email@email.com1");
        user.setPassword("Password@1123");
        return user;
    }
}
