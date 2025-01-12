package io.github.nishadchayanakhawa.taskvault.services.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Base exception class for all exceptions in the TaskVault application.
 * 
 * This class is used to represent exceptions with an associated HTTP status and
 * reason.
 */
public class TaskVaultExceptions extends RuntimeException {

	/**
	 * The HTTP status associated with the exception.
	 */
	private final HttpStatus status;

	/**
	 * A brief reason or description explaining the exception.
	 */
	private final String reason;

	/**
	 * Serial version UID for ensuring compatibility during deserialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new TaskVaultExceptions with a specific message, HTTP status,
	 * and reason.
	 * 
	 * @param message the error message to be passed to the exception.
	 * @param status  the HTTP status associated with the exception.
	 * @param reason  the reason describing the exception.
	 */
	public TaskVaultExceptions(String message, HttpStatus status, String reason) {
		super(message); // Passes the error message to the superclass (RuntimeException)
		this.status = status; // Initializes the HTTP status
		this.reason = reason; // Initializes the reason for the exception
	}

	/**
	 * Gets the HTTP status code as an integer.
	 * 
	 * @return the HTTP status code of the exception.
	 */
	public int getStatus() {
		return this.status.value(); // Returns the numeric value of the HTTP status
	}

	/**
	 * Gets the error phrase associated with the HTTP status.
	 * 
	 * @return the reason phrase of the HTTP status (e.g., "NOT FOUND", "BAD
	 *         REQUEST").
	 */
	public String getError() {
		return this.status.getReasonPhrase(); // Returns the HTTP status's reason phrase
	}

	/**
	 * Gets the reason associated with the exception.
	 * 
	 * @return the reason explaining the cause of the exception.
	 */
	public String getReason() {
		return this.reason; // Returns the reason provided during construction
	}

	/**
	 * Gets the HttpStatus object representing the exception's HTTP status.
	 * 
	 * @return the HttpStatus of the exception.
	 */
	public HttpStatus getHttpStatus() {
		return this.status; // Returns the HttpStatus object
	}
}
