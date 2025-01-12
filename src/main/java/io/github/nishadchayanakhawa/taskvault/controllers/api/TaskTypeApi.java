package io.github.nishadchayanakhawa.taskvault.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nishadchayanakhawa.taskvault.model.dto.TaskTypeDTO;
import io.github.nishadchayanakhawa.taskvault.services.TaskTypeService;

/**
 * REST controller for managing Task Type configurations.
 * 
 * Provides endpoints for creating, retrieving, updating, and deleting task
 * types, exposing the functionality as an API.
 */
@RestController // Marks this class as a RESTful controller
@RequestMapping("/api/configuration/taskType") // Base path for all task type API endpoints
public class TaskTypeApi {

	// Service to handle task type operations
	private TaskTypeService taskTypeService;

	@Autowired
	public TaskTypeApi(TaskTypeService taskTypeService) {
		// Constructor injection for TaskTypeService to decouple dependencies
		this.taskTypeService = taskTypeService;
	}

	/**
	 * Endpoint to create or update a task type.
	 * 
	 * @param taskTypeToSave the TaskTypeDTO containing task type data.
	 * @return ResponseEntity with the saved TaskTypeDTO and appropriate HTTP
	 *         status.
	 */
	@PutMapping
	public ResponseEntity<TaskTypeDTO> save(@RequestBody TaskTypeDTO taskTypeToSave) {
		// Determine HTTP status: CREATED for new task types, OK for updates
		HttpStatus status = taskTypeToSave.getId() == null ? HttpStatus.CREATED : HttpStatus.OK;
		// Save the task type and return the response
		return new ResponseEntity<>(this.taskTypeService.save(taskTypeToSave), status);
	}

	/**
	 * Endpoint to retrieve a task type by its ID.
	 * 
	 * @param id the ID of the task type to retrieve.
	 * @return ResponseEntity containing the TaskTypeDTO and HTTP status OK.
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<TaskTypeDTO> get(@PathVariable Long id) {
		// Fetch the task type by ID and return the response
		return new ResponseEntity<>(this.taskTypeService.get(id), HttpStatus.OK);
	}

	/**
	 * Endpoint to retrieve all task types.
	 * 
	 * @return ResponseEntity containing a list of TaskTypeDTO objects and HTTP
	 *         status OK.
	 */
	@GetMapping
	public ResponseEntity<List<TaskTypeDTO>> getAll() {
		// Fetch all task types and return the response
		return new ResponseEntity<>(this.taskTypeService.getAll(), HttpStatus.OK);
	}

	/**
	 * Endpoint to delete a task type.
	 * 
	 * @param taskTypeToDeleteDto the TaskTypeDTO containing the ID of the task type
	 *                            to delete.
	 * @return ResponseEntity with HTTP status OK.
	 */
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody TaskTypeDTO taskTypeToDeleteDto) {
		// Delete the task type by its ID
		this.taskTypeService.delete(taskTypeToDeleteDto.getId());
		// Return a successful response
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
