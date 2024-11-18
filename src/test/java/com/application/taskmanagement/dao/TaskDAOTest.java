package com.application.taskmanagement.dao;

import com.application.taskmanagement.db.DB;
import com.application.taskmanagement.dto.TaskDTO;
import com.application.taskmanagement.enums.Status;
import com.application.taskmanagement.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the TaskDAOImpl class. This class tests various DAO methods such as
 * creating, updating, deleting, and fetching tasks from a mock database.
 */
@ExtendWith(MockitoExtension.class)
public class TaskDAOTest {
    @InjectMocks
    private TaskDAOImpl taskDAO;

    @Test
    public void testGetAllTasks(){
        // mocking util method
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            DB.tasks.put("1",new Task());
            DB.tasks.put("2",new Task());
            List<Task> tasks = taskDAO.getAllTasks();
            assertNotNull(tasks);
            assertEquals(2, tasks.size());

            DB.tasks.clear();
            tasks = taskDAO.getAllTasks();
            assertNotNull(tasks);
            assertEquals(0, tasks.size());
        }
    }

    @Test
    public void testGetTaskById(){
        // mocking util method
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            DB.tasks.put("1",new Task());
            Task task = taskDAO.getTaskById("1");
            assertNotNull(task);
            DB.tasks.clear();

            taskDAO.getTaskById("1");
        } catch (Exception e) {
            assertEquals("Task with id 1 not found", e.getMessage());
        }
    }

    @Test
    public void testCreateTask(){
        // mocking util method
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            DB.tasks.put("1",new Task());
            Task task = taskDAO.createTask(getMockTaskDTOData());
            assertNotNull(task);
        }
    }

    @Test
    public void testDeleteTask(){
        // mocking util method
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            DB.tasks.put("1",new Task());
            Task task = taskDAO.deleteTask("1");
            assertNotNull(task);
        }
    }

    @Test
    public void testUpdateTask(){
        // mocking util method
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            DB.tasks.put("1",getMockTaskData());
            Task task = taskDAO.updateTask("1", getMockTaskDTOData());
            assertNotNull(task);
        }
    }

    @Test
    public void testUpdateTaskStatus(){
        // mocking util method
        try(MockedStatic<DB> mockedStatic = Mockito.mockStatic(DB.class)){
            DB.tasks.put("1",getMockTaskData());
            Task task = taskDAO.updateTaskStatus("1", Status.COMPLETED);
            assertNotNull(task);
        }
    }

    /**
     * Utility method to mock task DTO data for testing.
     *
     * @return A TaskDTO with mock data.
     */
    private TaskDTO getMockTaskDTOData(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("title");
        taskDTO.setDescription("description");
        taskDTO.setDueDate("28-11-2024");
        return taskDTO;
    }

    /**
     * Utility method to mock task data for testing.
     *
     * @return A Task with mock data.
     */
    private Task getMockTaskData(){
        Task task = new Task();
        task.setTitle("title");
        task.setDescription("description");
        task.setStatus(Status.PENDING);
        task.setDueDate(new Date());
        return task;
    }
}
