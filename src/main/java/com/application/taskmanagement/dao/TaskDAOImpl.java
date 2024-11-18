package com.application.taskmanagement.dao;

import com.application.taskmanagement.db.DB;
import com.application.taskmanagement.dto.TaskDTO;
import com.application.taskmanagement.enums.Status;
import com.application.taskmanagement.model.Task;
import com.application.taskmanagement.util.TaskUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the {@link TaskDAO} interface.
 * Provides concrete methods to manage tasks in the in-memory database.
 */
@Service
public class TaskDAOImpl implements TaskDAO{

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public List<Task> getAllTasks() {
        // if db is empty
        if(DB.tasks.isEmpty()) {
            return new ArrayList<>();
        }
        // if db is not empty
        return new ArrayList<>(DB.tasks.values());
    }

    @Override
    public Task getTaskById(String id) {
        // if task found
        if(validateId(id)) {
            return DB.tasks.get(id);
        }
        // if task not found
        throw new RuntimeException("Task with id " + id + " not found");
    }

    @Override
    public Task createTask(TaskDTO task) {
        // generation unique id
        String id = TaskUtil.generateId();

        // validation logic for task
        if(validateId(id)) {
            throw new RuntimeException("Task with id " + id + " already exists");
        }
        validateTaskFields(task);

        // create task
        Task newTask = new Task();
        newTask.setId(id);
        newTask.setTitle(task.getTitle().trim());
        newTask.setDescription(task.getDescription().trim());
        newTask.setStatus(Status.PENDING);

        // validate due date
        newTask.setDueDate(validateDate(task.getDueDate()));

        // set created and updated date
        newTask.setCreatedAt(new Date());
        newTask.setUpdatedAt(new Date());

        // add task to db
        DB.tasks.put(id, newTask);
        return newTask;
    }

    @Override
    public Task deleteTask(String id) {
        // if task found
        if(validateId(id)) {
            return DB.tasks.remove(id);
        }
        // if task not found
        throw new RuntimeException("Task with id " + id + " not found");
    }

    @Override
    public Task updateTask(String id, TaskDTO task) {
        // getting task from id
        Task taskToUpdate = getTaskById(id);
        // assign non null values to task
        updateNonNullValues(taskToUpdate, task);
        // update task in db
        DB.tasks.put(id, taskToUpdate);
        return taskToUpdate;
    }

    @Override
    public Task updateTaskStatus(String id, Status status) {
        // getting task from id
        Task taskToUpdate = getTaskById(id);
        // checking null status
        validateStatus(status);
        // update task in db
        taskToUpdate.setStatus(status);
        taskToUpdate.setUpdatedAt(new Date());
        DB.tasks.put(id, taskToUpdate);
        return taskToUpdate;
    }

    /**
     * Validates if a task ID exists in the in-memory database.
     *
     * @param id the unique identifier to validate.
     * @return {@code true} if the ID exists, otherwise {@code false}.
     */
    private boolean validateId(String id) {
        return DB.tasks.containsKey(id);
    }

    /**
     * Validates the fields of a {@link TaskDTO} to ensure they are not null or empty.
     *
     * @param task the {@link TaskDTO} object to validate.
     * @throws RuntimeException if any field is invalid.
     */
    private void validateTaskFields(TaskDTO task) {
        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Task title cannot be empty");
        }
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            throw new RuntimeException("Task description cannot be empty");
        }
        if (task.getDueDate() == null) {
            throw new RuntimeException("Task due date cannot be empty");
        }
    }

    /**
     * Updates non-null and valid fields of an existing task.
     *
     * @param taskToUpdate the {@link Task} object to update.
     * @param task the {@link TaskDTO} containing new values.
     * @throws RuntimeException if no fields are updated or if the new due date is invalid.
     */
    private void updateNonNullValues(Task taskToUpdate, TaskDTO task) {
        boolean isUpdated = false;

        if (task.getTitle() != null && !task.getTitle().trim().isEmpty() && !task.getTitle().equals(taskToUpdate.getTitle().trim())) {
            taskToUpdate.setTitle(task.getTitle().trim());
            isUpdated = true;
        }
        if (task.getDescription() != null && !task.getDescription().isEmpty() && !task.getDescription().equals(taskToUpdate.getDescription().trim())) {
            taskToUpdate.setDescription(task.getDescription().trim());
            isUpdated = true;
        }
        // validate due date format
        if (task.getDueDate() != null) {
            Date date = validateDate(task.getDueDate());
            if(date.equals(taskToUpdate.getDueDate())) {
                throw new RuntimeException("Date cannot be same");
            }
            taskToUpdate.setDueDate(date);
            isUpdated = true;
        }
        if (task.getStatus() != null && !task.getStatus().equals(taskToUpdate.getStatus())) {
            taskToUpdate.setStatus(task.getStatus());
            isUpdated = true;
        }
        taskToUpdate.setUpdatedAt(new Date());

        if(!isUpdated) {
            throw new RuntimeException("No fields to update");
        }
    }

    /**
     * Validates the {@link Status} to ensure it is not null.
     *
     * @param status the {@link Status} to validate.
     * @throws RuntimeException if the status is null.
     */
    private void validateStatus(Status status) {
        if (status == null) {
            throw new RuntimeException("Status cannot be null");
        }
    }

    /**
     * Validates and parses a date string into a {@link Date} object.
     * Ensures the date is in the correct format and not in the past.
     *
     * @param newDate the date string to validate.
     * @return the parsed {@link Date} object.
     * @throws RuntimeException if the date is invalid or in the past.
     */
    private Date validateDate(String newDate) {
        try {
            Date date = formatter.parse(newDate);
            // checking if date is in the past
            if(date.before(new Date())) {
                throw new RuntimeException("Date cannot be in the past");
            }
            return date;
        } catch (RuntimeException e){
            throw new RuntimeException("Date cannot be in the past");
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format");
        }
    }
}
