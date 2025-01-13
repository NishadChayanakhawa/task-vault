package io.github.nishadchayanakhawa.taskvault.services;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.nishadchayanakhawa.taskvault.model.dto.TaskGroupDTO;
import io.github.nishadchayanakhawa.taskvault.model.TaskGroup;
import io.github.nishadchayanakhawa.taskvault.repositories.TaskGroupRepository;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.DuplicateRecordException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.EntityNotFoundException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.TransactionException;
import jakarta.validation.ConstraintViolationException;

/**
 * Service class for managing TaskGroup entities.
 * 
 * This class provides methods to create, retrieve, update, and delete task
 * groups. It leverages JPA repositories for database operations and ModelMapper
 * for mapping between entities and DTOs.
 */
@Service
@Transactional // Ensures methods are executed within a transaction context
public class TaskGroupService {

	// Logger for logging debug and error messages
	private static final Logger logger = (Logger) LoggerFactory.getLogger(TaskGroupService.class);

	// Repository for interacting with the TaskGroup database table
	private TaskGroupRepository taskGroupRepository;

	// ModelMapper instance for mapping between entities and DTOs
	private ModelMapper modelMapper;

	@Autowired
	public TaskGroupService(TaskGroupRepository taskGroupRepository, ModelMapper modelMapper) {
		// Dependency injection for TaskGroupRepository to handle database operations
		this.taskGroupRepository = taskGroupRepository;

		// Dependency injection for ModelMapper to handle object mapping (DTOs to
		// entities and vice versa)
		this.modelMapper = modelMapper;
	}

	/**
	 * Saves a new or updates an existing TaskGroup.
	 * 
	 * @param taskGroupDto the TaskGroupDTO to be saved or updated.
	 * @return the saved or updated TaskGroupDTO.
	 * @throws DuplicateRecordException if a task group with the same name already
	 *                                  exists.
	 * @throws TransactionException     for validation errors or other transaction
	 *                                  issues.
	 */
	public TaskGroupDTO save(TaskGroupDTO taskGroupDto) {
		logger.debug("Saving task group: {}", taskGroupDto);
		try {
			// Map DTO to entity, save to the database, then map back to DTO
			TaskGroupDTO savedTaskGroupDTO = modelMapper.map(
					this.taskGroupRepository.saveAndFlush(modelMapper.map(taskGroupDto, TaskGroup.class)),
					TaskGroupDTO.class);
			logger.debug("Saved task group: {}", savedTaskGroupDTO);
			return savedTaskGroupDTO;
		} catch (DataIntegrityViolationException e) {
			// Thrown when a unique constraint (e.g., name) is violated
			logger.debug("Error while saving {}. Will throw DuplicateRecordException", taskGroupDto, e);
			throw new DuplicateRecordException("Task Group", "name", taskGroupDto.getName());
		} catch (ConstraintViolationException e) {
			// Thrown for other validation errors
			logger.debug("Error while saving {}. Will throw TransactionException", taskGroupDto, e);
			throw new TransactionException(e);
		}
	}

	/**
	 * Retrieves a TaskGroup by its ID.
	 * 
	 * @param id the ID of the TaskGroup to retrieve.
	 * @return the TaskGroupDTO representing the retrieved entity.
	 * @throws EntityNotFoundException if no TaskGroup with the given ID exists.
	 */
	public TaskGroupDTO get(Long id) {
		return this.modelMapper.map(
				this.taskGroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task Group", id)),
				TaskGroupDTO.class);
	}

	/**
	 * Retrieves all TaskGroups in the database.
	 * 
	 * @return a list of TaskGroupDTO objects representing all task groups.
	 */
	public List<TaskGroupDTO> getAll() {
		return this.taskGroupRepository.findAll().stream()
				.map(taskGroup -> modelMapper.map(taskGroup, TaskGroupDTO.class)).toList();
	}

	/**
	 * Deletes a TaskGroup by its ID.
	 * 
	 * @param id the ID of the TaskGroup to delete. No exception is thrown if the
	 *           entity does not exist.
	 */
	public void delete(Long id) {
		this.taskGroupRepository.deleteById(id);
	}
}
