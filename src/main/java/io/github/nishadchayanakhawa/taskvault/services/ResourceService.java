package io.github.nishadchayanakhawa.taskvault.services;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.nishadchayanakhawa.taskvault.model.dto.ResourceDTO;
import io.github.nishadchayanakhawa.taskvault.model.Resource;
import io.github.nishadchayanakhawa.taskvault.repositories.ResourceRepository;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.DuplicateRecordException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.EntityNotFoundException;
import io.github.nishadchayanakhawa.taskvault.services.exceptions.TransactionException;
import jakarta.validation.ConstraintViolationException;

/**
 * Service class for managing Resource entities.
 * 
 * This class provides methods to create, retrieve, update, and delete resource.
 * It leverages JPA repositories for database operations and ModelMapper
 * for mapping between entities and DTOs.
 */
@Service
@Transactional // Ensures methods are executed within a transaction context
public class ResourceService {

	// Logger for logging debug and error messages
	private static final Logger logger = (Logger) LoggerFactory.getLogger(ResourceService.class);

	// Repository for interacting with the Resource database table
	private ResourceRepository resourceRepository;

	// ModelMapper instance for mapping between entities and DTOs
	private ModelMapper modelMapper;

	@Autowired
	public ResourceService(ResourceRepository resourceRepository, ModelMapper modelMapper) {
		// Dependency injection for ResourceRepository to handle database operations
		this.resourceRepository = resourceRepository;

		// Dependency injection for ModelMapper to handle object mapping (DTOs to
		// entities and vice versa)
		this.modelMapper = modelMapper;
	}

	/**
	 * Saves a new or updates an existing Resource.
	 * 
	 * @param resourceDto the ResourceDTO to be saved or updated.
	 * @return the saved or updated ResourceDTO.
	 * @throws DuplicateRecordException if a resource with the same name already
	 *                                  exists.
	 * @throws TransactionException     for validation errors or other transaction
	 *                                  issues.
	 */
	public ResourceDTO save(ResourceDTO resourceDto) {
		logger.debug("Saving resource: {}", resourceDto);
		try {
			// Map DTO to entity, save to the database, then map back to DTO
			ResourceDTO savedResourceDTO = modelMapper.map(
					this.resourceRepository.saveAndFlush(modelMapper.map(resourceDto, Resource.class)),
					ResourceDTO.class);
			logger.debug("Saved resource: {}", savedResourceDTO);
			return savedResourceDTO;
		} catch (DataIntegrityViolationException e) {
			// Thrown when a unique constraint (e.g., name) is violated
			logger.debug("Error while saving {}. Will throw DuplicateRecordException", resourceDto, e);
			throw new DuplicateRecordException("Task Group", "name", resourceDto.getName());
		} catch (ConstraintViolationException e) {
			// Thrown for other validation errors
			logger.debug("Error while saving {}. Will throw TransactionException", resourceDto, e);
			throw new TransactionException(e);
		}
	}

	/**
	 * Retrieves a Resource by its ID.
	 * 
	 * @param id the ID of the Resource to retrieve.
	 * @return the ResourceDTO representing the retrieved entity.
	 * @throws EntityNotFoundException if no Resource with the given ID exists.
	 */
	public ResourceDTO get(Long id) {
		return this.modelMapper.map(
				this.resourceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Task Group", id)),
				ResourceDTO.class);
	}

	/**
	 * Retrieves all Resources in the database.
	 * 
	 * @return a list of ResourceDTO objects representing all resources.
	 */
	public List<ResourceDTO> getAll() {
		return this.resourceRepository.findAll().stream()
				.map(resource -> modelMapper.map(resource, ResourceDTO.class)).toList();
	}

	/**
	 * Deletes a Resource by its ID.
	 * 
	 * @param id the ID of the Resource to delete. No exception is thrown if the
	 *           entity does not exist.
	 */
	public void delete(Long id) {
		this.resourceRepository.deleteById(id);
	}
}
