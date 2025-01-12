package io.github.nishadchayanakhawa.taskvault.services.exceptions;

import java.util.stream.Collectors;

// Spring libraries
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ConstraintViolation;

/**
 * <b>Class Name</b>: TransactionException<br>
 * <b>Description</b>: This exception is thrown when there is a validation or
 * transactional error in the application, particularly when the data being
 * processed doesn't meet the constraints required for the entity. The exception
 * is associated with an HTTP status code of 400 (Bad Request).
 * 
 * This class extends the `TaskVaultExceptions` class to provide a detailed
 * error message when validation constraints are violated.<br>
 * 
 * It uses `ConstraintViolationException` to collect constraint violation
 * messages and formats them.
 * 
 * @author nishad.chayanakhawa
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request data is incorrect.")
public class TransactionException extends TaskVaultExceptions {

	/**
	 * Serial version UID for ensuring compatibility during deserialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new TransactionException with a specific error message.
	 * 
	 * @param message the error message to be included with the exception.
	 */
	public TransactionException(String message) {
		// Passes the error message, HTTP status, and reason to the superclass
		// (TaskVaultExceptions)
		super(message, HttpStatus.BAD_REQUEST, "Request data is incorrect");
	}

	/**
	 * Constructs a new TransactionException based on a
	 * ConstraintViolationException.
	 * 
	 * This constructor is used when a validation error occurs, and it provides a
	 * detailed error message based on the violations that occurred in the data. The
	 * violations are passed to a helper method to format them into a message
	 * string.
	 * 
	 * @param e the ConstraintViolationException that encapsulates the validation
	 *          errors.
	 */
	public TransactionException(ConstraintViolationException e) {
		// Extracts violation messages and passes them to the constructor
		this(TransactionException.extractMessages(e));
	}

	/**
	 * Extracts the violation messages from the ConstraintViolationException and
	 * joins them into a single string.
	 * 
	 * This method is responsible for processing the validation errors and returning
	 * a combined message that lists all the violations, separated by a semicolon.
	 * 
	 * @param e the ConstraintViolationException that contains the validation
	 *          violations.
	 * @return a string with all violation messages joined by a semicolon.
	 */
	private static String extractMessages(ConstraintViolationException e) {
		// Collects all constraint violation messages into a single string, separated by
		// semicolons.
		return e.getConstraintViolations().stream().map(ConstraintViolation::getMessage) // Extracts the violation
																							// message
				.collect(Collectors.joining(";")); // Joins all messages with a semicolon
	}
}
