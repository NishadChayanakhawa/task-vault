package io.github.nishadchayanakhawa.taskvault.model;

// JPA annotations
import jakarta.persistence.Column;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Lombok annotations for boilerplate code generation
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "TV_TASK", uniqueConstraints = {
		// Ensures that the 'SUMMARY' column has unique values
		@UniqueConstraint(name = "UNIQUE_TV_NAME_GROUP", columnNames = { "TASK_GROUP_ID", "NAME" }) })
@Getter
@Setter
@NoArgsConstructor
public class Task {
	// Unique identifier for the Task entity
	@Id
	@GeneratedValue
	private Long id;

	// Association with TaskType entity (many-to-one relationship)
	@ManyToOne
	@JoinColumn(name = "TASK_TYPE_ID", nullable = false)
	@NotNull(message = "task type {notnull.message}")
	private TaskType taskType;

	// Association with TaskGroup entity (many-to-one relationship)
	@ManyToOne
	@JoinColumn(name = "TASK_GROUP_ID", nullable = false)
	@NotNull(message = "task group {notnull.message}")
	private TaskGroup taskGroup;

	// name of the task
	@Column(nullable = false)
	@NotBlank(message = "name {notblank.message}")
	private String name;

	// Priority of the task, stored as an ordinal value in the database
	@Column(nullable = false)
	@NotNull(message = "priority {notnull.message}")
	@Enumerated(EnumType.ORDINAL)
	private Priority priority;

	// Due date for the task
	@Column
	private LocalDate dueDate;

	// Transient field for calculating days until the due date
	@Transient
	private Integer dueInDays;

	// Getter for dueInDays that calculates the difference between dueDate and today
	public Integer getDueInDays() {
		if (dueDate != null) {
			return (int) ChronoUnit.DAYS.between(LocalDate.now(), dueDate);
		}
		return null; // Return null if dueDate is not set
	}
}
