Feature: Task type configuration api tests

	Scenario: Add task type configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskTypeConfiguration/addTaskTypeConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 201
		And Save value at Json Path "id" in response, to variable "addedTaskTypeId"
		
	Scenario: Add invalid task type configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskTypeConfiguration/invalidTaskTypeConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 400
		
	Scenario: Add duplicate task type configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskTypeConfiguration/addTaskTypeConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 409
		
	Scenario: Edit task type configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskTypeConfiguration/editTaskTypeConfiguration.json"
		And In request body template, replace "${id}" with value of variable "addedTaskTypeId"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 200
		
	Scenario: Get task type configuration
		When GET request is submitted to "http://localhost:8999/api/configuration/taskType/{{addedTaskTypeId}}"
		Then Response status code should be 200
		
	Scenario: Get all task type configurations
		When GET request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 200
		
	Scenario: Delete task type configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "TaskTypeConfiguration/deleteTaskTypeConfiguration.json"
		And In request body template, replace "${id}" with value of variable "addedTaskTypeId"
		When DELETE request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 200
		
	Scenario: Get deleted task type configuration
		When GET request is submitted to "http://localhost:8999/api/configuration/taskType/{{addedTaskTypeId}}"
		Then Response status code should be 410