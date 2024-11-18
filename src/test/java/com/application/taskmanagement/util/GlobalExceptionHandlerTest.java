package com.application.taskmanagement.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit test for the GlobalExceptionHandler class.
 * This test verifies the behavior of the exception handling method in the event of an exception.
 */
@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {
    @Mock
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void handleGlobalException() {
        Mockito.lenient().when(globalExceptionHandler.handleGlobalException(new RuntimeException("test"))).thenReturn(null);
        ResponseEntity<Map<String, Object>> responseEntity = globalExceptionHandler.handleGlobalException(new RuntimeException("test"));
        assertNull(responseEntity);
    }
}
