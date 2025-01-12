package io.github.nishadchayanakhawa.taskvault.services;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.nishadchayanakhawa.taskvault.model.dto.TaskTypeDTO;
import io.github.nishadchayanakhawa.taskvault.model.TaskType;
import io.github.nishadchayanakhawa.taskvault.repositories.TaskTypeRepository;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.DuplicateRecordException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.EntityNotFoundException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.TransactionException;
import jakarta.validation.ConstraintViolationException;

/**
 * Service class for managing TaskType entities.
 * 
 * This class provides methods to create, retrieve, update, and delete task
 * types. It leverages JPA repositories for database operations and ModelMapper
 * for mapping between entities and DTOs.
 */
@Service
@Transactional // Ensures methods are executed within a transaction context
public class TaskTypeService {

	// Logger for logging debug and error messages
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TaskTypeService.class);

	// Repository for interacting with the TaskType database table
	private TaskTypeRepository taskTypeRepository;

	// ModelMapper instance for mapping between entities and DTOs
	private ModelMapper modelMapper;

	@Autowired
	public TaskTypeService(TaskTypeRepository taskTypeRepository, ModelMapper modelMapper) {
		// Dependency injection for TaskTypeRepository to handle database operations
		this.taskTypeRepository = taskTypeRepository;

		// Dependency injection for ModelMapper to handle object mapping (DTOs to
		// entities and vice versa)
		this.modelMapper = modelMapper;
	}

	/**
	 * Saves a new or updates an existing TaskType.
	 * 
	 * @param taskTypeDto the TaskTypeDTO to be saved or updated.
	 * @return the saved or updated TaskTypeDTO.
	 * @throws DuplicateRecordException if a task type with the same name already
	 *                                  exists.
	 * @throws TransactionException     for validation errors or other transaction
	 *                                  issues.
	 */
	public TaskTypeDTO save(TaskTypeDTO taskTypeDto) {
		logger.debug("Saving task type: {}", taskTypeDto);
		try {
			// Map DTO to entity, save to the database, then map back to DTO
			TaskTypeDTO savedTaskTypeDTO = modelMapper.map(
					this.taskTypeRepository.saveAndFlush(modelMapper.map(taskTypeDto, TaskType.class)),
					TaskTypeDTO.class);
			logger.debug("Saved task type: {}", savedTaskTypeDTO);
			return savedTaskTypeDTO;
		} catch (DataIntegrityViolationException e) {
			// Thrown when a unique constraint (e.g., name) is violated
			logger.debug("Error while saving {}. Will throw DuplicateRecordException", taskTypeDto, e);
			throw new DuplicateRecordException("Task Type", "name", taskTypeDto.getName());
		} catch (ConstraintViolationException e) {
			// Thrown for other validation errors
			logger.debug("Error while saving {}. Will throw TransactionException", taskTypeDto, e);
			throw new TransactionException(e);
		}
	}

	/**
	 * Retrieves a TaskType by its ID.
	 * 
	 * @param id the ID of the TaskType to retrieve.
	 * @return the TaskTypeDTO representing the retrieved entity.
	 * @throws EntityNotFoundException if no TaskType with the given ID exists.
	 */
	public TaskTypeDTO get(Long id) {
		return this.modelMapper.map(
				this.taskTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task Type", id)),
				TaskTypeDTO.class);
	}

	/**
	 * Retrieves all TaskTypes in the database.
	 * 
	 * @return a list of TaskTypeDTO objects representing all task types.
	 */
	public List<TaskTypeDTO> getAll() {
		return this.taskTypeRepository.findAll().stream().map(taskType -> modelMapper.map(taskType, TaskTypeDTO.class))
				.toList();
	}

	/**
	 * Deletes a TaskType by its ID.
	 * 
	 * @param id the ID of the TaskType to delete. No exception is thrown if the
	 *           entity does not exist.
	 */
	public void delete(Long id) {
		this.taskTypeRepository.deleteById(id);
	}
}
