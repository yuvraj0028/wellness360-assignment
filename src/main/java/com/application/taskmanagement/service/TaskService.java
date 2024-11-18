package com.application.taskmanagement.service;

import com.application.taskmanagement.dto.TaskDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Service interface for managing tasks.
 * Provides methods for CRUD operations and task status updates.
 */
public interface TaskService {
    /**
     * Retrieves all tasks.
     *
     * @return a {@link ResponseEntity} containing a map with task details.
     */
    public ResponseEntity<Map<String, Object>> getAllTasks();

    /**
     * Retrieves a specific task by its ID.
     *
     * @param id the unique identifier of the task.
     * @return a {@link ResponseEntity} containing the task details.
     */
    public ResponseEntity<Map<String, Object>> getTaskById(String id);

    /**
     * Creates a new task.
     *
     * @param taskDTO the task details provided by the client.
     * @return a {@link ResponseEntity} with the created task's details.
     */
    public ResponseEntity<Map<String, Object>> createTask(TaskDTO taskDTO);

    /**
     * Deletes a task by its ID.
     *
     * @param id the unique identifier of the task to be deleted.
     * @return a {@link ResponseEntity} with a confirmation or error message.
     */
    public ResponseEntity<Map<String, Object>> deleteTask(String id);

    /**
     * Updates an existing task's details.
     *
     * @param id      the unique identifier of the task to be updated.
     * @param taskDTO the updated task details.
     * @return a {@link ResponseEntity} with the updated task's details.
     */
    public ResponseEntity<Map<String, Object>> updateTask(String id, TaskDTO taskDTO);

    /**
     * Marks a task as completed by updating its status.
     *
     * @param id the unique identifier of the task.
     * @return a {@link ResponseEntity} with the updated task's details.
     */
    public ResponseEntity<Map<String, Object>> updateTaskStatus(String id);
}
