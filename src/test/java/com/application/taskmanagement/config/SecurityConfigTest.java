package com.application.taskmanagement.config;

import com.application.taskmanagement.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link SecurityConfig} class.
 * Verifies the configuration of authentication, password encoding, and user details service.
 */
public class SecurityConfigTest {

    @InjectMocks
    private SecurityConfig securityConfig;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserDetailsService() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        assertThat(userDetailsService).isNotNull();
        assertThat(userDetailsService).isInstanceOf(AuthService.class);
    }

    @Test
    void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertThat(passwordEncoder).isNotNull();
        String encodedPassword = passwordEncoder.encode("testPassword");
        assertThat(encodedPassword).isNotBlank();
        assertThat(passwordEncoder.matches("testPassword", encodedPassword)).isTrue();
    }

    @Test
    void testAuthenticationManager() throws Exception {
        AuthenticationManager mockManager = mock(AuthenticationManager.class);
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(mockManager);

        AuthenticationManager authenticationManager = securityConfig.authenticationManager(authenticationConfiguration);

        assertThat(authenticationManager).isNotNull();
        assertThat(authenticationManager).isSameAs(mockManager);
        verify(authenticationConfiguration, times(1)).getAuthenticationManager();
    }
}
