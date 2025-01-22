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

import io.github.nishadchayanakhawa.taskvault.model.dto.TaskDTO;
import io.github.nishadchayanakhawa.taskvault.services.TaskService;

/**
 * REST controller for managing Task Group configurations.
 * 
 * Provides endpoints for creating, retrieving, updating, and deleting tasks
 * , exposing the functionality as an API.
 */
@RestController // Marks this class as a RESTful controller
@RequestMapping("/api/task") // Base path for all task API endpoints
public class TaskApi {

	// Service to handle task operations
	private TaskService taskService;

	@Autowired
	public TaskApi(TaskService taskService) {
		// Constructor injection for TaskService to decouple dependencies
		this.taskService = taskService;
	}

	/**
	 * Endpoint to create or update a task.
	 * 
	 * @param taskToSave the TaskDTO containing task data.
	 * @return ResponseEntity with the saved TaskDTO and appropriate HTTP
	 *         status.
	 */
	@PutMapping
	public ResponseEntity<TaskDTO> save(@RequestBody TaskDTO taskToSave) {
		// Determine HTTP status: CREATED for new tasks, OK for updates
		HttpStatus status = taskToSave.getId() == null ? HttpStatus.CREATED : HttpStatus.OK;
		// Save the task and return the response
		return new ResponseEntity<>(this.taskService.save(taskToSave), status);
	}

	/**
	 * Endpoint to retrieve a task by its ID.
	 * 
	 * @param id the ID of the task to retrieve.
	 * @return ResponseEntity containing the TaskDTO and HTTP status OK.
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<TaskDTO> get(@PathVariable Long id) {
		// Fetch the task by ID and return the response
		return new ResponseEntity<>(this.taskService.get(id), HttpStatus.OK);
	}

	/**
	 * Endpoint to retrieve all tasks.
	 * 
	 * @return ResponseEntity containing a list of TaskDTO objects and HTTP
	 *         status OK.
	 */
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAll() {
		// Fetch all tasks and return the response
		return new ResponseEntity<>(this.taskService.getAll(), HttpStatus.OK);
	}

	/**
	 * Endpoint to delete a task.
	 * 
	 * @param taskToDeleteDto the TaskDTO containing the ID of the task
	 *                            to delete.
	 * @return ResponseEntity with HTTP status OK.
	 */
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody TaskDTO taskToDeleteDto) {
		// Delete the task by its ID
		this.taskService.delete(taskToDeleteDto.getId());
		// Return a successful response
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
