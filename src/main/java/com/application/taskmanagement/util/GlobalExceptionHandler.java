package com.application.taskmanagement.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * Global exception handler to catch all exceptions across the application.
 * Provides centralized handling for exceptions and generates appropriate responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles global exceptions and generates a response with error details.
     *
     * @param ex The exception to be handled.
     * @return A ResponseEntity containing the error details and HTTP status.
     */
    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;

        responseData = TaskUtil.responseJson(null, ex.getCause()+" "+ex.getMessage()+" "+ex.getClass());
        responseEntity = TaskUtil.handleException(responseData, HttpStatus.BAD_REQUEST);

        return responseEntity;
    }
}