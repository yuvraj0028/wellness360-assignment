package com.application.taskmanagement.controller;

import com.application.taskmanagement.dto.TaskDTO;
import com.application.taskmanagement.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for the TaskController class. This class tests various API methods
 * such as creating, updating, deleting, and fetching tasks using mock services.
 */
@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Test
    public void testGetAllTasks() {
        Mockito.lenient().when(taskService.getAllTasks()).thenReturn(getMockResponseEntity());
        ResponseEntity<Map<String, Object>> responseEntity = taskController.getAllTasks();
        assertNotNull(responseEntity);
    }

    @Test
    public void testGetTaskById() {
        Mockito.lenient().when(taskService.getTaskById("1")).thenReturn(getMockResponseEntity());
        ResponseEntity<Map<String, Object>> responseEntity = taskController.getTaskById("1");
        assertNotNull(responseEntity);
    }

    @Test
    public void testCreateTask() {
        Mockito.lenient().when(taskService.createTask(new TaskDTO())).thenReturn(getMockResponseEntity());
        ResponseEntity<Map<String, Object>> responseEntity = taskController.createTask(new TaskDTO());
        assertNull(responseEntity);
    }

    @Test
    public void testDeleteTask() {
        Mockito.lenient().when(taskService.deleteTask("1")).thenReturn(getMockResponseEntity());
        ResponseEntity<Map<String, Object>> responseEntity = taskController.deleteTask("1");
        assertNotNull(responseEntity);
    }

    @Test
    public void testUpdateTask() {
        Mockito.lenient().when(taskService.updateTask("1", new TaskDTO())).thenReturn(getMockResponseEntity());
        ResponseEntity<Map<String, Object>> responseEntity = taskController.updateTask("1", new TaskDTO());
        assertNull(responseEntity);
    }

    @Test
    public void testUpdateTaskStatus() {
        Mockito.lenient().when(taskService.updateTaskStatus("1")).thenReturn(getMockResponseEntity());
        ResponseEntity<Map<String, Object>> responseEntity = taskController.updateTaskStatus("1");
        assertNotNull(responseEntity);
    }

    /**
     * Utility method to mock a ResponseEntity for testing purposes.
     *
     * @return A mock ResponseEntity with an empty map and 200 status.
     */
    private ResponseEntity<Map<String, Object>> getMockResponseEntity() {
        return new ResponseEntity<>(Map.of(), null, 200);
    }
}
