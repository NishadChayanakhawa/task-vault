Feature: Task type configuration api tests

	Scenario: Add resource configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "ResourceConfiguration/addResourceConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/resource"
		Then Response status code should be 201
		And Save value at Json Path "id" in response, to variable "addedResourceId"
		
	Scenario: Add invalid resource configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "ResourceConfiguration/invalidResourceConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/resource"
		Then Response status code should be 400
		
	Scenario: Add duplicate resource configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "ResourceConfiguration/addResourceConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/resource"
		Then Response status code should be 409
		
	Scenario: Edit resource configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "ResourceConfiguration/editResourceConfiguration.json"
		And In request body template, replace "${id}" with value of variable "addedResourceId"
		When PUT request is submitted to "http://localhost:8999/api/configuration/resource"
		Then Response status code should be 200
		
	Scenario: Get resource configuration
		When GET request is submitted to "http://localhost:8999/api/configuration/resource/{{addedResourceId}}"
		Then Response status code should be 200
		
	Scenario: Get all resource configurations
		When GET request is submitted to "http://localhost:8999/api/configuration/resource"
		Then Response status code should be 200
		
	Scenario: Delete resource configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "ResourceConfiguration/deleteResourceConfiguration.json"
		And In request body template, replace "${id}" with value of variable "addedResourceId"
		When DELETE request is submitted to "http://localhost:8999/api/configuration/resource"
		Then Response status code should be 200
		
	Scenario: Get deleted resource configuration
		When GET request is submitted to "http://localhost:8999/api/configuration/resource/{{addedResourceId}}"
		Then Response status code should be 410