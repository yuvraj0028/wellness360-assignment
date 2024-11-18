package com.application.taskmanagement.service;

import com.application.taskmanagement.dao.TaskDAO;
import com.application.taskmanagement.dto.TaskDTO;
import com.application.taskmanagement.enums.Status;
import com.application.taskmanagement.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the TaskServiceImpl class. This class tests the service layer methods
 * for managing tasks, including retrieving, creating, updating, deleting, and changing the status of tasks.
 */
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskDAO taskDAO;

    @Test
    public void testGetAllTasks() {
        Mockito.when(taskDAO.getAllTasks()).thenReturn(List.of(new Task()));
        ResponseEntity<Map<String, Object>> tasks = taskService.getAllTasks();
        assertNotNull(tasks);
    }

    @Test
    public void testGetAllTasksException(){
        Mockito.when(taskDAO.getAllTasks()).thenThrow(RuntimeException.class);
        ResponseEntity<Map<String, Object>> tasks = taskService.getAllTasks();
        assertNotNull(tasks);
    }

    @Test
    public void testGetTaskById() {
        Mockito.when(taskDAO.getTaskById("1")).thenReturn(new Task());
        ResponseEntity<Map<String, Object>> task = taskService.getTaskById("1");
        assertNotNull(task);
    }

    @Test
    public void testGetTaskByIdException(){
        Mockito.when(taskDAO.getTaskById("1")).thenThrow(RuntimeException.class);
        ResponseEntity<Map<String, Object>> task = taskService.getTaskById("1");
        assertNotNull(task);
    }

    @Test
    public void testCreateTask() {
        Mockito.when(taskDAO.createTask(new TaskDTO())).thenReturn(new Task());
        ResponseEntity<Map<String, Object>> task = taskService.createTask(new TaskDTO());
        assertNotNull(task);
    }

    @Test
    public void testCreateTaskException(){
        Mockito.when(taskDAO.createTask(new TaskDTO())).thenThrow(RuntimeException.class);
        ResponseEntity<Map<String, Object>> task = taskService.createTask(new TaskDTO());
        assertNotNull(task);
    }

    @Test
    public void testUpdateTask() {
        Mockito.when(taskDAO.updateTask("1", new TaskDTO())).thenReturn(new Task());
        ResponseEntity<Map<String, Object>> task = taskService.updateTask("1", new TaskDTO());
        assertNotNull(task);
    }

    @Test
    public void testUpdateTaskException(){
        Mockito.when(taskDAO.updateTask("1", new TaskDTO())).thenThrow(RuntimeException.class);
        ResponseEntity<Map<String, Object>> task = taskService.updateTask("1", new TaskDTO());
        assertNotNull(task);
    }

    @Test
    public void testDeleteTask() {
        Mockito.when(taskDAO.deleteTask("1")).thenReturn(new Task());
        ResponseEntity<Map<String, Object>> task = taskService.deleteTask("1");
        assertNotNull(task);
    }

    @Test
    public void testDeleteTaskException(){
        Mockito.when(taskDAO.deleteTask("1")).thenThrow(RuntimeException.class);
        ResponseEntity<Map<String, Object>> task = taskService.deleteTask("1");
        assertNotNull(task);
    }

    @Test
    public void testUpdateTaskStatus() {
        Mockito.when(taskDAO.updateTaskStatus("1", Status.COMPLETED)).thenReturn(new Task());
        ResponseEntity<Map<String, Object>> task = taskService.updateTaskStatus("1");
        assertNotNull(task);
    }

    @Test
    public void testUpdateTaskStatusException(){
        Mockito.when(taskDAO.updateTaskStatus("1", Status.COMPLETED)).thenThrow(RuntimeException.class);
        ResponseEntity<Map<String, Object>> task = taskService.updateTaskStatus("1");
        assertNotNull(task);
    }

}
