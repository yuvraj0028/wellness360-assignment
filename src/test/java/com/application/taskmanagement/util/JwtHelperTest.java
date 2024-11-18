package com.application.taskmanagement.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link JwtHelper} class.
 * Ensures proper functionality for token generation, extraction, validation, and expiration handling.
 */
@ExtendWith(MockitoExtension.class)
public class JwtHelperTest {

    private JwtHelper jwtHelper;

    @BeforeEach
    void setUp() {
        jwtHelper = new JwtHelper();
        jwtHelper.setSecret("YmFzZTY0U2VjcmV0S2VjcmV0S2V5U3RyaW5nRm9yVGVzdGluZw=="); // Valid Base64 secret
        jwtHelper.setExpiration(300000); // 5 minutes in milliseconds
    }

    @Test
    void testGenerateTokenAndExtractUserName() {
        String userName = "testUser";

        String token = jwtHelper.generateToken(userName);

        assertThat(token).isNotNull();

        String extractedUserName = jwtHelper.extractUserName(token);
        assertThat(extractedUserName).isEqualTo(userName);
    }

    @Test
    void testExtractExpiration() {
        String token = jwtHelper.generateToken("testUser");

        Date expiration = jwtHelper.extractExpiration(token);

        assertThat(expiration).isNotNull();
        assertThat(expiration.after(new Date())).isTrue();
    }

    @Test
    void testIsTokenExpired_ShouldReturnFalseForValidToken() {
        String token = jwtHelper.generateToken("testUser");

        boolean isExpired = jwtHelper.isTokenExpired(token);

        assertThat(isExpired).isFalse();
    }

    @Test
    void testValidateToken_ShouldReturnTrueForValidToken() {
        String userName = "testUser";
        String token = jwtHelper.generateToken(userName);

        UserDetails userDetails = User.withUsername(userName).password("password").authorities("ROLE_USER").build();

        boolean isValid = jwtHelper.validateToken(token, userDetails);

        assertThat(isValid).isTrue();
    }

    @Test
    void testValidateToken_ShouldReturnFalseForInvalidUser() {
        String token = jwtHelper.generateToken("testUser");

        UserDetails userDetails = User.withUsername("otherUser").password("password").authorities("ROLE_USER").build();

        boolean isValid = jwtHelper.validateToken(token, userDetails);

        assertThat(isValid).isFalse();
    }
}
