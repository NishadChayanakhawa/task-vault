package io.github.nishadchayanakhawa.taskvault.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.nishadchayanakhawa.taskvault.model.Task;

/**
 * Repository interface for managing Task entities.
 * 
 * This interface provides basic CRUD (Create, Read, Update, Delete) and
 * additional JPA-based operations for the TaskGroup entity. It extends
 * JpaRepository, which comes with several built-in methods.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
	// No additional methods defined here, as JpaRepository provides:
	// - findAll(): Retrieves all TaskType records
	// - findById(Long id): Finds a TaskType by its ID
	// - save(TaskType entity): Saves or updates a TaskType
	// - deleteById(Long id): Deletes a TaskType by its ID
	// and many more...
}
