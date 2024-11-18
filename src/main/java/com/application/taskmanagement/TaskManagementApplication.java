package com.application.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Task Management Application.
 * <p>
 * This class is responsible for bootstrapping and launching the Spring Boot application.
 * It uses the {@link SpringApplication} class to start the application and configure the
 * necessary components automatically via the {@link SpringBootApplication} annotation.
 * </p>
 *
 * @author Yuvraj Singh
 * @version 1.0
 * @since 18-11-2024
 */
@SpringBootApplication
public class TaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}

}
