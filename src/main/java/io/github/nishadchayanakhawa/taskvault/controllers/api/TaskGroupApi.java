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

import io.github.nishadchayanakhawa.taskvault.model.dto.TaskGroupDTO;
import io.github.nishadchayanakhawa.taskvault.services.TaskGroupService;

/**
 * REST controller for managing Task Group configurations.
 * 
 * Provides endpoints for creating, retrieving, updating, and deleting task
 * groups, exposing the functionality as an API.
 */
@RestController // Marks this class as a RESTful controller
@RequestMapping("/api/configuration/taskGroup") // Base path for all task group API endpoints
public class TaskGroupApi {

	// Service to handle task group operations
	private TaskGroupService taskGroupService;

	@Autowired
	public TaskGroupApi(TaskGroupService taskGroupService) {
		// Constructor injection for TaskGroupService to decouple dependencies
		this.taskGroupService = taskGroupService;
	}

	/**
	 * Endpoint to create or update a task group.
	 * 
	 * @param taskGroupToSave the TaskGroupDTO containing task group data.
	 * @return ResponseEntity with the saved TaskGroupDTO and appropriate HTTP
	 *         status.
	 */
	@PutMapping
	public ResponseEntity<TaskGroupDTO> save(@RequestBody TaskGroupDTO taskGroupToSave) {
		// Determine HTTP status: CREATED for new task groups, OK for updates
		HttpStatus status = taskGroupToSave.getId() == null ? HttpStatus.CREATED : HttpStatus.OK;
		// Save the task group and return the response
		return new ResponseEntity<>(this.taskGroupService.save(taskGroupToSave), status);
	}

	/**
	 * Endpoint to retrieve a task group by its ID.
	 * 
	 * @param id the ID of the task group to retrieve.
	 * @return ResponseEntity containing the TaskGroupDTO and HTTP status OK.
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<TaskGroupDTO> get(@PathVariable Long id) {
		// Fetch the task group by ID and return the response
		return new ResponseEntity<>(this.taskGroupService.get(id), HttpStatus.OK);
	}

	/**
	 * Endpoint to retrieve all task groups.
	 * 
	 * @return ResponseEntity containing a list of TaskGroupDTO objects and HTTP
	 *         status OK.
	 */
	@GetMapping
	public ResponseEntity<List<TaskGroupDTO>> getAll() {
		// Fetch all task groups and return the response
		return new ResponseEntity<>(this.taskGroupService.getAll(), HttpStatus.OK);
	}

	/**
	 * Endpoint to delete a task group.
	 * 
	 * @param taskGroupToDeleteDto the TaskGroupDTO containing the ID of the task group
	 *                            to delete.
	 * @return ResponseEntity with HTTP status OK.
	 */
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody TaskGroupDTO taskGroupToDeleteDto) {
		// Delete the task group by its ID
		this.taskGroupService.delete(taskGroupToDeleteDto.getId());
		// Return a successful response
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
