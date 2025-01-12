package io.github.nishadchayanakhawa.taskvault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Task Vault application.
 * 
 * This class serves as the entry point for the Spring Boot application. It
 * initializes and starts the application context.
 */
@SpringBootApplication // Marks this as a Spring Boot application
public class TaskVaultApplication {

	/**
	 * Main method to launch the Task Vault application.
	 * 
	 * @param args command-line arguments passed during application startup.
	 */
	public static void main(String[] args) {
		// Launch the Spring Boot application
		SpringApplication.run(TaskVaultApplication.class, args);
	}
}
