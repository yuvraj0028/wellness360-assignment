package com.application.taskmanagement.filter;

import com.application.taskmanagement.service.AuthService;
import com.application.taskmanagement.util.JwtHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link JwtFilter} class.
 * Verifies the behavior of JWT token validation and request filtering logic.
 */
@ExtendWith(MockitoExtension.class)
public class JwtFilterTest {

    @InjectMocks
    private JwtFilter jwtFilter;

    @Mock
    private JwtHelper jwtHelper;

    @Mock
    private AuthService authService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        // Mock invalid token behavior
        String token = "invalid-token";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtHelper.extractUserName(token)).thenReturn(null);

        // Invoke the filter
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Verify no authentication is set and filter continues
        verify(jwtHelper, never()).validateToken(anyString(), any(UserDetails.class));
        verify(authService, never()).loadUserByUsername(anyString());
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_NoAuthHeader() throws ServletException, IOException {
        // Mock no Authorization header
        when(request.getHeader("Authorization")).thenReturn(null);

        // Invoke the filter
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Verify no interaction with JWT helper or authService
        verify(jwtHelper, never()).extractUserName(anyString());
        verify(authService, never()).loadUserByUsername(anyString());
        verify(filterChain).doFilter(request, response);
    }
}
