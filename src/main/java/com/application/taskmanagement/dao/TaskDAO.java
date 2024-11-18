package com.application.taskmanagement.dao;

import com.application.taskmanagement.dto.TaskDTO;
import com.application.taskmanagement.enums.Status;
import com.application.taskmanagement.model.Task;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing tasks.
 * Provides methods for performing CRUD operations and status updates on tasks.
 */
public interface TaskDAO {
    /**
     * Retrieves a list of all tasks.
     *
     * @return a {@link List} of {@link Task} objects representing all tasks in the system.
     */
    public List<Task> getAllTasks();

    /**
     * Retrieves a task by its unique identifier.
     *
     * @param id the unique identifier of the task.
     * @return the {@link Task} object representing the task, if found.
     */
    public Task getTaskById(String id);

    /**
     * Creates a new task.
     *
     * @param task the {@link TaskDTO} object containing the details of the task to be created.
     * @return the {@link Task} object representing the created task.
     */
    public Task createTask(TaskDTO task);

    /**
     * Deletes a task by its unique identifier.
     *
     * @param id the unique identifier of the task to be deleted.
     * @return the {@link Task} object representing the deleted task, if the deletion was successful.
     */
    public Task deleteTask(String id);

    /**
     * Updates an existing task with new details.
     *
     * @param id the unique identifier of the task to be updated.
     * @param task the {@link TaskDTO} object containing the updated task details.
     * @return the {@link Task} object representing the updated task.
     */
    public Task updateTask(String id, TaskDTO task);

    /**
     * Updates the status of a task.
     *
     * @param id the unique identifier of the task whose status is to be updated.
     * @param status the new {@link Status} value to be set for the task.
     * @return the {@link Task} object representing the task with the updated status.
     */
    public Task updateTaskStatus(String id, Status status);
}
