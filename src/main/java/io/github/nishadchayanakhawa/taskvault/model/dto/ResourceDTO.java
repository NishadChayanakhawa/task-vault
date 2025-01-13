package io.github.nishadchayanakhawa.taskvault.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Data Transfer Object (DTO) for Resource.
 * 
 * This class is used to transfer resource data between different layers (e.g.,
 * from the service layer to the controller or vice versa). It helps decouple
 * the domain entity from external API representations.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResourceDTO {

	/**
	 * Unique identifier for the resource. Represents the database ID for the
	 * Resource entity.
	 */
	private Long id;

	/**
	 * Name of the resource. Represents the descriptive name for the resource.
	 */
	private String name;
}
