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

import io.github.nishadchayanakhawa.taskvault.model.dto.ResourceDTO;
import io.github.nishadchayanakhawa.taskvault.services.ResourceService;

/**
 * REST controller for managing Resource configurations.
 * 
 * Provides endpoints for creating, retrieving, updating, and deleting task
 * resources, exposing the functionality as an API.
 */
@RestController // Marks this class as a RESTful controller
@RequestMapping("/api/configuration/resource") // Base path for all resource API endpoints
public class ResourceApi {

	// Service to handle resource operations
	private ResourceService resourceService;

	@Autowired
	public ResourceApi(ResourceService resourceService) {
		// Constructor injection for ResourceService to decouple dependencies
		this.resourceService = resourceService;
	}

	/**
	 * Endpoint to create or update a resource.
	 * 
	 * @param resourceToSave the ResourceDTO containing resource data.
	 * @return ResponseEntity with the saved ResourceDTO and appropriate HTTP
	 *         status.
	 */
	@PutMapping
	public ResponseEntity<ResourceDTO> save(@RequestBody ResourceDTO resourceToSave) {
		// Determine HTTP status: CREATED for new resources, OK for updates
		HttpStatus status = resourceToSave.getId() == null ? HttpStatus.CREATED : HttpStatus.OK;
		// Save the resource and return the response
		return new ResponseEntity<>(this.resourceService.save(resourceToSave), status);
	}

	/**
	 * Endpoint to retrieve a resource by its ID.
	 * 
	 * @param id the ID of the resource to retrieve.
	 * @return ResponseEntity containing the ResourceDTO and HTTP status OK.
	 */
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResourceDTO> get(@PathVariable Long id) {
		// Fetch the resource by ID and return the response
		return new ResponseEntity<>(this.resourceService.get(id), HttpStatus.OK);
	}

	/**
	 * Endpoint to retrieve all resources.
	 * 
	 * @return ResponseEntity containing a list of ResourceDTO objects and HTTP
	 *         status OK.
	 */
	@GetMapping
	public ResponseEntity<List<ResourceDTO>> getAll() {
		// Fetch all resources and return the response
		return new ResponseEntity<>(this.resourceService.getAll(), HttpStatus.OK);
	}

	/**
	 * Endpoint to delete a resource.
	 * 
	 * @param resourceToDeleteDto the ResourceDTO containing the ID of the resource
	 *                            to delete.
	 * @return ResponseEntity with HTTP status OK.
	 */
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody ResourceDTO resourceToDeleteDto) {
		// Delete the resource by its ID
		this.resourceService.delete(resourceToDeleteDto.getId());
		// Return a successful response
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
