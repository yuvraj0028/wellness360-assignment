package com.application.taskmanagement.controller;

import com.application.taskmanagement.dto.TaskDTO;
import com.application.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for managing tasks in the Task Management application.
 * Provides endpoints for CRUD operations and additional task status updates.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Retrieves all tasks.
     *
     * @return a {@link ResponseEntity} containing a map with all tasks and their details.
     */
    @GetMapping(produces = "application/json",name = "getAllTasks")
    ResponseEntity<Map<String, Object>> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param id the unique identifier of the task.
     * @return a {@link ResponseEntity} containing a map with the task details, if found.
     */
    @GetMapping(value = "/{id}", produces = "application/json",name = "getTaskById")
    ResponseEntity<Map<String, Object>> getTaskById(@PathVariable(required = true, name = "id") String id) {
        return taskService.getTaskById(id);
    }

    /**
     * Creates a new task.
     *
     * @param taskDTO the data transfer object containing the details of the task to be created.
     * @return a {@link ResponseEntity} containing a map with the created task details.
     */
    @PostMapping(produces = "application/json", consumes = "application/json" ,name = "createTask")
    ResponseEntity<Map<String, Object>> createTask(@RequestBody(required = true) TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id the unique identifier of the task to be deleted.
     * @return a {@link ResponseEntity} containing a confirmation of the task deletion.
     */
    @DeleteMapping(value = "/{id}", produces = "application/json",name = "deleteTask")
    ResponseEntity<Map<String, Object>> deleteTask(@PathVariable(required = true, name = "id") String id) {
        return taskService.deleteTask(id);
    }

    /**
     * Updates an existing task with new details.
     *
     * @param id the unique identifier of the task to be updated.
     * @param taskDTO the data transfer object containing the updated task details.
     * @return a {@link ResponseEntity} containing a map with the updated task details.
     */
    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json" ,name = "updateTask")
    ResponseEntity<Map<String, Object>> updateTask(@PathVariable(required = true, name = "id") String id, @RequestBody(required = true) TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    /**
     * Updates the status of a task to 'completed'.
     *
     * @param id the unique identifier of the task whose status is to be updated.
     * @return a {@link ResponseEntity} containing a confirmation of the task status update.
     */
    @PatchMapping(value="/{id}/complete",produces = "application/json",name = "updateTaskStatus")
    ResponseEntity<Map<String, Object>> updateTaskStatus(@PathVariable(required = true, name = "id") String id) {
        return taskService.updateTaskStatus(id);
    }
}
