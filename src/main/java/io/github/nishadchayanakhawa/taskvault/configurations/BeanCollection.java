package io.github.nishadchayanakhawa.taskvault.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Configuration class for managing bean definitions.
 * 
 * This class centralizes the creation and management of beans that will be used
 * across the application. Beans defined here are registered in the Spring
 * application context.
 */
@Component
public class BeanCollection {

	/**
	 * Provides a ModelMapper bean for object mapping.
	 * 
	 * ModelMapper is used to map between different object types, such as mapping
	 * entity objects to DTOs and vice versa.
	 * 
	 * @return a new instance of ModelMapper.
	 */
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
