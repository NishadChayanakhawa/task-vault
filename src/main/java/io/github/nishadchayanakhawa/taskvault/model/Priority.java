package io.github.nishadchayanakhawa.taskvault.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Priority {
	// Enum constants representing different priority levels
	CRITICAL("CRITICAL", "01 - Critical"), // critical
	HIGH("HIGH", "02 - High"), // high
	MEDIUM("MEDIUM", "03 - Medium"), // medium
	LOW("LOW", "04 - Low");// low

	// Code representing the priority level (used internally or for serialization)
	private String code;

	// Display value for the priority level (used for user-friendly representation)
	private String displayValue;

	// Constructor to initialize enum constants with code and display value
	Priority(String code, String displayValue) {
		this.code = code;
		this.displayValue = displayValue;
	}
}
