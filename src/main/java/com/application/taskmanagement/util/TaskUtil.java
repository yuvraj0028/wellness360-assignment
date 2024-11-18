package com.application.taskmanagement.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Utility class for task-related operations, such as generating unique IDs
 * and preparing response data for API responses.
 */
public class TaskUtil {
    /**
     * Generates a unique ID using UUID and returns it as a string.
     *
     * @return A unique ID as a string without dashes.
     */
    public static String generateId() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * Prepares a map containing the response data and error message for API responses.
     *
     * @param responseData The response data to be included in the map.
     * @param errorMessage The error message (if any) to be included in the map.
     * @return A map containing the response data and error message.
     */
    public static Map<String,Object> responseJson(Object responseData, Object errorMessage) {
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("responseData",responseData);
        responseMap.put("errorMessage",errorMessage);
        return responseMap;
    }

    /**
     * Creates and returns a ResponseEntity with the provided data and HTTP status.
     *
     * @param data   The data to be returned in the response.
     * @param status The HTTP status for the response.
     * @return A ResponseEntity containing the data and HTTP status.
     */
    public static ResponseEntity<Map<String, Object>> handleException(Map<String, Object> data, HttpStatus status) {
        return new ResponseEntity<>( data, status);
    }
}
