package io.github.nishadchayanakhawa.taskvault.services.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class for handling duplicate record scenarios.
 * 
 * This exception is thrown when an attempt is made to create or update an
 * entity with a field value that violates a unique constraint.
 */
public class DuplicateRecordException extends TaskVaultExceptions {

	/**
	 * Serial version UID for ensuring compatibility during deserialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new DuplicateRecordException with details about the duplicate
	 * record.
	 * 
	 * @param entityName the name of the entity where the duplicate was detected.
	 * @param fieldName  the name of the field causing the duplicate.
	 * @param value      the value of the field that is duplicated.
	 */
	public DuplicateRecordException(String entityName, String fieldName, String value) {
		// Passes a formatted error message, HTTP status, and error type to the
		// superclass
		super(String.format("%s '%s' already exists for %s", fieldName, value, entityName), HttpStatus.CONFLICT,
				"Duplicate record");
	}
}
