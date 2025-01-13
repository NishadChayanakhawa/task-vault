package io.github.nishadchayanakhawa.taskvault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a task type in the TaskVault system. This entity is used to
 * categorize tasks into different types.
 */
@Entity
@Table(name = "TV_TASK_GROUP", // Maps this entity to the "TV_TASK_GROUP" table in the database
		uniqueConstraints = { @UniqueConstraint(name = "UNIQUE_TV_TASK_GROUP_NAME", // Ensures unique names for task
																					// groups
				columnNames = { "NAME" } // Applies the constraint to the "NAME" column
		) })
@Getter
@Setter
@NoArgsConstructor
public class TaskGroup {

	/**
	 * Unique identifier for the task group. This value is automatically generated
	 * by the database.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Name of the task group. This is a mandatory field and must not be blank.
	 */
	@NotBlank(message = "Task group name is required")
	private String name;
}
