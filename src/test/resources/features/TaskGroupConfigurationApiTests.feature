Feature: Task type configuration api tests

	Scenario: Add task group configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskGroupConfiguration/addTaskGroupConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 201
		And Save value at Json Path "id" in response, to variable "addedTaskGroupId"
		
	Scenario: Add invalid task group configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskGroupConfiguration/invalidTaskGroupConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 400
		
	Scenario: Add duplicate task group configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskGroupConfiguration/addTaskGroupConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 409
		
	Scenario: Edit task group configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskGroupConfiguration/editTaskGroupConfiguration.json"
		And In request body template, replace "${id}" with value of variable "addedTaskGroupId"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 200
		
	Scenario: Get task group configuration
		When GET request is submitted to "http://localhost:8999/api/configuration/taskGroup/{{addedTaskGroupId}}"
		Then Response status code should be 200
		
	Scenario: Get all task group configurations
		When GET request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 200
		
	Scenario: Delete task group configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskGroupConfiguration/deleteTaskGroupConfiguration.json"
		And In request body template, replace "${id}" with value of variable "addedTaskGroupId"
		When DELETE request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 200
		
	Scenario: Get deleted task group configuration
		When GET request is submitted to "http://localhost:8999/api/configuration/taskGroup/{{addedTaskGroupId}}"
		Then Response status code should be 410