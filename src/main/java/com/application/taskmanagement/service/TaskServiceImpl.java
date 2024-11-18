package com.application.taskmanagement.service;

import com.application.taskmanagement.dao.TaskDAO;
import com.application.taskmanagement.dto.TaskDTO;
import com.application.taskmanagement.enums.Status;
import com.application.taskmanagement.model.Task;
import com.application.taskmanagement.util.TaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link TaskService}.
 * Handles task-related business logic and interacts with the data access layer.
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public ResponseEntity<Map<String, Object>> getAllTasks() {
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;
        try {
            List<Task> tasks = taskDAO.getAllTasks();
            log.info("fetched tasks of size {}", tasks.size());
            responseData = TaskUtil.responseJson(tasks, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching tasks", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Map<String, Object>> getTaskById(String id) {
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;
        try {
            Task task = taskDAO.getTaskById(id);
            log.info("fetched task {}", task);
            responseData = TaskUtil.responseJson(task, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.FOUND);
        } catch (RuntimeException e){
            log.error("Error fetching task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error fetching task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createTask(TaskDTO taskDTO) {
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;

        try {
            Task task = taskDAO.createTask(taskDTO);
            log.info("created task {}", task);
            responseData = TaskUtil.responseJson(task, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.CREATED);
        } catch (RuntimeException e){
            log.error("Error creating task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Error creating task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteTask(String id) {
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;

        try {
            Task task = taskDAO.deleteTask(id);
            log.info("deleted task {}", task);
            responseData = TaskUtil.responseJson(task, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.OK);
        } catch (RuntimeException e){
            log.error("Error deleting task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error deleting task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateTask(String id, TaskDTO taskDTO) {
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;

        try {
            Task task = taskDAO.updateTask(id, taskDTO);
            log.info("updated task {}", task);
            responseData = TaskUtil.responseJson(task, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.OK);
        } catch (RuntimeException e){
            log.error("Error updating task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error updating task", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateTaskStatus(String id) {
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> responseData = null;

        try {
            Task task = taskDAO.updateTaskStatus(id, Status.COMPLETED);
            log.info("updated task status {} to {}", task, Status.COMPLETED);
            responseData = TaskUtil.responseJson(task, null);
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.OK);
        } catch (RuntimeException e){
            log.error("Error updating task status", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Error updating task status", e);
            responseData = TaskUtil.responseJson(null, e.getMessage());
            responseEntity = TaskUtil.handleException(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
