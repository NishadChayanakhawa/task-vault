package io.github.nishadchayanakhawa.taskvault.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) for TaskType.
 * 
 * This class is used to transfer task type data between different layers (e.g.,
 * from the service layer to the controller or vice versa). It helps decouple
 * the domain entity from external API representations.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TaskTypeDTO {

	/**
	 * Unique identifier for the task type. Represents the database ID for the
	 * TaskType entity.
	 */
	private Long id;

	/**
	 * Name of the task type. Represents the descriptive name for the task type.
	 */
	private String name;
}
