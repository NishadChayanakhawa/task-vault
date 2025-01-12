package io.github.nishadchayanakhawa.taskvault.services.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling scenarios where an entity is not found.
 * 
 * This exception is thrown when a requested entity does not exist in the
 * database.
 */
public class EntityNotFoundException extends TaskVaultExceptions {

	/**
	 * Serial version UID for ensuring compatibility during deserialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new EntityNotFoundException with details about the missing
	 * entity.
	 * 
	 * @param entityName the name of the entity that was not found.
	 * @param id         the ID of the entity that was not found.
	 */
	public EntityNotFoundException(String entityName, Long id) {
		// Passes a formatted error message, HTTP status, and error type to the
		// superclass
		super(String.format("%s not found for id %s", entityName, id), HttpStatus.GONE, "Entity not found");
	}
}
