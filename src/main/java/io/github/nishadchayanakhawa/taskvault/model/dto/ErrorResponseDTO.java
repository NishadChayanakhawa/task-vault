package io.github.nishadchayanakhawa.taskvault.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data Transfer Object (DTO) for representing error responses.
 * 
 * This class is used to encapsulate error details returned to the client in
 * case of exceptions or validation failures.
 */
@Getter // Generates getter methods for all fields
@AllArgsConstructor // Generates a constructor with all fields as parameters
public class ErrorResponseDTO {

	/**
	 * HTTP status code of the error response.
	 */
	int status;

	/**
	 * Error type or category.
	 */
	String error;

	/**
	 * A brief message describing the error.
	 */
	String message;

	/**
	 * A list of detailed messages or errors, typically for validation issues.
	 */
	List<String> details;
}
