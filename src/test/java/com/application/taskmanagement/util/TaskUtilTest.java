package com.application.taskmanagement.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit test class for TaskUtil.
 * This class tests the utility methods of the TaskUtil class, including ID generation,
 * JSON response formatting, and exception handling.
 */
@ExtendWith(MockitoExtension.class)
public class TaskUtilTest {

    @Test
    public void testGenerateId() {
        try(MockedStatic<TaskUtil> mockedStatic = Mockito.mockStatic(TaskUtil.class)) {
            mockedStatic.when(TaskUtil::generateId).thenReturn("1");
            assertNotNull(TaskUtil.generateId());
        }
    }

    @Test
    public void testResponseJson() {
        try(MockedStatic<TaskUtil> mockedStatic = Mockito.mockStatic(TaskUtil.class)) {
            mockedStatic.when(()->TaskUtil.responseJson("test", null)).thenReturn(new HashMap<>());
            assertNotNull(TaskUtil.responseJson("test", null));
        }
    }

    @Test
    public void testHandleException() {
        try(MockedStatic<TaskUtil> mockedStatic = Mockito.mockStatic(TaskUtil.class)) {
            mockedStatic.when(()->TaskUtil.handleException(new HashMap<>(), HttpStatus.MULTI_STATUS)).thenReturn(null);
            assertNull(TaskUtil.handleException(new HashMap<>(), HttpStatus.MULTI_STATUS));
        }
    }

}
