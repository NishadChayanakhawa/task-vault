package io.github.nishadchayanakhawa.taskvault.services;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.nishadchayanakhawa.taskvault.model.dto.TaskDTO;
import io.github.nishadchayanakhawa.taskvault.model.Task;
import io.github.nishadchayanakhawa.taskvault.repositories.TaskRepository;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.DuplicateRecordException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.EntityNotFoundException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.TransactionException;
import jakarta.validation.ConstraintViolationException;

/**
 * Service class for managing Task entities.
 * 
 * This class provides methods to create, retrieve, update, and delete task
 * groups. It leverages JPA repositories for database operations and ModelMapper
 * for mapping between entities and DTOs.
 */
@Service
@Transactional // Ensures methods are executed within a transaction context
public class TaskService {

	// Logger for logging debug and error messages
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TaskService.class);

	// Repository for interacting with the Task database table
	private TaskRepository taskRepository;

	// ModelMapper instance for mapping between entities and DTOs
	private ModelMapper modelMapper;

	@Autowired
	public TaskService(TaskRepository taskRepository, ModelMapper modelMapper) {
		// Dependency injection for TaskRepository to handle database operations
		this.taskRepository = taskRepository;

		// Dependency injection for ModelMapper to handle object mapping (DTOs to
		// entities and vice versa)
		this.modelMapper = modelMapper;
	}

	/**
	 * Saves a new or updates an existing Task.
	 * 
	 * @param taskDto the TaskDTO to be saved or updated.
	 * @return the saved or updated TaskDTO.
	 * @throws DuplicateRecordException if a task with the same name already exists.
	 * @throws TransactionException     for validation errors or other transaction
	 *                                  issues.
	 */
	public TaskDTO save(TaskDTO taskDto) {
		logger.debug("Saving task: {}", taskDto);
		try {
			// Map DTO to entity, save to the database, then map back to DTO
			TaskDTO savedTaskDTO = modelMapper
					.map(this.taskRepository.saveAndFlush(modelMapper.map(taskDto, Task.class)), TaskDTO.class);
			logger.debug("Saved task: {}", savedTaskDTO);
			return savedTaskDTO;
		} catch (DataIntegrityViolationException e) {
			// Thrown when a unique constraint (e.g., name) is violated
			logger.debug("Error while saving {}. Will throw DuplicateRecordException", taskDto, e);
			throw new DuplicateRecordException("Task", "name", taskDto.getName());
		} catch (ConstraintViolationException e) {
			// Thrown for other validation errors
			logger.debug("Error while saving {}. Will throw TransactionException", taskDto, e);
			throw new TransactionException(e);
		}
	}

	/**
	 * Retrieves a Task by its ID.
	 * 
	 * @param id the ID of the Task to retrieve.
	 * @return the TaskDTO representing the retrieved entity.
	 * @throws EntityNotFoundException if no Task with the given ID exists.
	 */
	public TaskDTO get(Long id) {
		return this.modelMapper.map(
				this.taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task", id)),
				TaskDTO.class);
	}

	/**
	 * Retrieves all Tasks in the database.
	 * 
	 * @return a list of TaskDTO objects representing all tasks.
	 */
	public List<TaskDTO> getAll() {
		return this.taskRepository.findAll().stream().map(task -> modelMapper.map(task, TaskDTO.class)).toList();
	}

	/**
	 * Deletes a Task by its ID.
	 * 
	 * @param id the ID of the Task to delete. No exception is thrown if the entity
	 *           does not exist.
	 */
	public void delete(Long id) {
		this.taskRepository.deleteById(id);
	}
}
