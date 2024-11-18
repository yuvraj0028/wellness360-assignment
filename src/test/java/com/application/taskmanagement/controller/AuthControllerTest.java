package com.application.taskmanagement.controller;

import com.application.taskmanagement.dto.UserDTO;
import com.application.taskmanagement.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for the {@link AuthController} class.
 * Verifies the behavior of login and signup methods using mocked dependencies.
 */
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Test
    public void testLogin() {
        Mockito.lenient().when(authService.login(getMockUserDTO())).thenReturn(getMockResponseEntity());
        assertNull(authController.login(getMockUserDTO()));
    }

    @Test
    public void testSignup() {
        Mockito.lenient().when(authService.signUp(getMockUserDTO())).thenReturn(getMockResponseEntity());
        assertNull(authController.signUp(getMockUserDTO()));
    }

    // mock data for testing UserDTO
    private UserDTO getMockUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email");
        userDTO.setPassword("password");
        return userDTO;
    }

    // mock data for testing ResponseEntity
    private ResponseEntity<Map<String,Object>> getMockResponseEntity() {
        Map<String,Object> map = Map.of("email","email");
        return new ResponseEntity<>(map, null, 200);
    }

}
