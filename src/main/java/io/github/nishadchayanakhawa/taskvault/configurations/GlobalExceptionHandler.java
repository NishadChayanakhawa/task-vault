package io.github.nishadchayanakhawa.taskvault.configurations;

import java.util.List;
import java.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.github.nishadchayanakhawa.taskvault.model.dto.ErrorResponseDTO;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.TaskVaultExceptions;

/**
 * Global exception handler for the Task Vault application.
 * 
 * This class centralizes the handling of exceptions across all controllers,
 * ensuring consistent error responses.
 */
@ControllerAdvice // Indicates this class provides global exception handling for controllers
public class GlobalExceptionHandler {

	/**
	 * Handles exceptions of type TaskVaultExceptions.
	 * 
	 * @param e the TaskVaultExceptions instance to handle.
	 * @return a ResponseEntity containing an ErrorResponseDTO with details of the
	 *         exception.
	 */
	@ExceptionHandler({ TaskVaultExceptions.class }) // Specifies the exception types to handle
	public ResponseEntity<ErrorResponseDTO> handleExceptions(TaskVaultExceptions e) {
		// Extract status, error message, reason, and details from the exception
		int status = e.getStatus();
		String error = e.getError();
		String reason = e.getReason();
		List<String> details = Arrays.asList(e.getMessage().split(";")); // Split the error details by ';'

		// Create an ErrorResponseDTO with the extracted information
		ErrorResponseDTO errorDetail = new ErrorResponseDTO(status, error, reason, details);

		// Return the error response with the appropriate HTTP status
		return new ResponseEntity<>(errorDetail, e.getHttpStatus());
	}
}
