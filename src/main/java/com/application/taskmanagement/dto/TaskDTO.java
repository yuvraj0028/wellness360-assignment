package com.application.taskmanagement.dto;

import com.application.taskmanagement.enums.Status;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for task data.
 * Encapsulates the title, description, status, and due date of a task.
 * Uses Lombok annotations to generate getters and setters.
 */
@Getter
@Setter
public class TaskDTO {
    private String title;
    private String description;
    private Status status;
    private String dueDate;
}
