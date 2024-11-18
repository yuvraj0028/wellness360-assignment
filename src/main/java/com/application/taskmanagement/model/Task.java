package com.application.taskmanagement.model;

import com.application.taskmanagement.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Represents a task in the task management system.
 * <p>
 * Fields include:
 * </p>
 * <ul>
 *     <li>{@code id}: Unique identifier for the task.</li>
 *     <li>{@code title}: Title of the task.</li>
 *     <li>{@code description}: Description of the task.</li>
 *     <li>{@code status}: Current status of the task (e.g., PENDING, IN_PROGRESS, COMPLETED).</li>
 *     <li>{@code dueDate}: Deadline for the task.</li>
 *     <li>{@code createdAt}: Timestamp when the task was created.</li>
 *     <li>{@code updatedAt}: Timestamp when the task was last updated.</li>
 * </ul>
 * <p>
 * Lombok annotations are used for generating boilerplate code such as getters, setters,
 * a no-argument constructor, and a string representation.
 * </p>
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Task {
     @NotBlank
    private String id;
     @NotBlank
    private String title;
     @NotBlank
    private String description;
    private Status status;
     @NotBlank
    private Date dueDate;
    private Date createdAt;
    private Date updatedAt;
}
