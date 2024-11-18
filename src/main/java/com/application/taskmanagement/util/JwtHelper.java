package com.application.taskmanagement.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JwtHelper is a utility class that provides methods for generating, validating, and extracting information from JSON Web Tokens (JWT).
 * It uses the io.jsonwebtoken library to handle the creation and validation of JWTs with HS256 algorithm.
 * The secret key for signing the token and the expiration time are configured via application properties.
 */
@Getter
@Setter
@Component
public class JwtHelper {

    // Secret key for signing JWTs, injected from application properties
    @Value("${jwt.secret}")
    private String secret;

    // Expiration time of the token in milliseconds, injected from application properties
    @Value("${jwt.expirationMs}")
    private long expiration;

    /**
     * Generates a JWT token for the specified username.
     * The token is signed with a secret key and includes the issued timestamp and expiration time.
     *
     * @param userName the username to include in the token
     * @return a JWT token as a string
     */
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();

        // Creating the token with claims, subject, issued date, and expiration
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)  // The subject of the token (typically the username)
                .setIssuedAt(new Date(System.currentTimeMillis()))  // The time the token was issued
                .setExpiration(new Date(System.currentTimeMillis() + expiration))  // The expiration time
                .signWith(getSignKey(), SignatureAlgorithm.HS256)  // Signing the token with HS256 algorithm
                .compact();  // Compacting the token to a string
    }

    /**
     * Retrieves the signing key used to sign JWTs.
     * The key is derived from the base64-encoded secret stored in the application properties.
     *
     * @return the signing key used for JWT signing
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);  // Decoding the base64-encoded secret
        return Keys.hmacShaKeyFor(keyBytes);  // Creating a HMAC key for signing the JWT
    }

    /**
     * Extracts the username (subject) from the provided JWT token.
     *
     * @param token the JWT token from which to extract the username
     * @return the username extracted from the token
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);  // Extracting the subject (username) claim
    }

    /**
     * Extracts the expiration date from the provided JWT token.
     *
     * @param token the JWT token from which to extract the expiration date
     * @return the expiration date extracted from the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);  // Extracting the expiration date claim
    }

    /**
     * Extracts a specific claim from the JWT token.
     * This method uses the provided claim resolver function to retrieve the claim.
     *
     * @param token the JWT token from which to extract the claim
     * @param claimResolver a function to extract a specific claim (e.g., subject or expiration)
     * @param <T> the type of the claim being extracted
     * @return the extracted claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);  // Extracting all claims from the token
        return claimResolver.apply(claims);  // Applying the resolver to extract the specific claim
    }

    /**
     * Extracts all claims from the provided JWT token.
     *
     * @param token the JWT token from which to extract all claims
     * @return the claims of the JWT token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())  // Setting the signing key for token verification
                .build()
                .parseClaimsJws(token)  // Parsing the JWT token
                .getBody();  // Returning the claims body
    }

    /**
     * Checks if the provided JWT token has expired.
     *
     * @param token the JWT token to check for expiration
     * @return true if the token has expired, false otherwise
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());  // Checking if the expiration date is before the current time
    }

    /**
     * Validates the provided JWT token by checking its expiration and matching the username with the user details.
     *
     * @param token the JWT token to validate
     * @param userDetails the user details to compare against
     * @return true if the token is valid, false otherwise
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);  // Extracting the username from the token
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));  // Checking if the username matches and the token is not expired
    }
}