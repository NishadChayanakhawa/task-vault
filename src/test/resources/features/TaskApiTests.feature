Feature: Task api tests

	#Setup
	Scenario: Add task group configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/addTaskGroupConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 201
		And Save value at Json Path "id" in response, to variable "taskGroupId"
		
	Scenario: Add task type configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/addTaskTypeConfiguration.json"
		When PUT request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 201
		And Save value at Json Path "id" in response, to variable "taskTypeId"
	
	#Test
	Scenario: Add task
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/addTask.json"
		And In request body template, replace "${taskGroupId}" with value of variable "taskGroupId"
		And In request body template, replace "${taskTypeId}" with value of variable "taskTypeId"
		When PUT request is submitted to "http://localhost:8999/api/task"
		Then Response status code should be 201
		And Save value at Json Path "id" in response, to variable "addedTaskId"
		
	Scenario: Add duplicate task
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/addTask.json"
		And In request body template, replace "${taskGroupId}" with value of variable "taskGroupId"
		And In request body template, replace "${taskTypeId}" with value of variable "taskTypeId"
		When PUT request is submitted to "http://localhost:8999/api/task"
		Then Response status code should be 409
		
	Scenario: Add invalid task
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/invalidTask.json"
		And In request body template, replace "${taskGroupId}" with value of variable "taskGroupId"
		And In request body template, replace "${taskTypeId}" with value of variable "taskTypeId"
		When PUT request is submitted to "http://localhost:8999/api/task"
		Then Response status code should be 400
		
	Scenario: Edit task
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/editTask.json"
		And In request body template, replace "${addedTaskId}" with value of variable "addedTaskId"
		And In request body template, replace "${taskGroupId}" with value of variable "taskGroupId"
		And In request body template, replace "${taskTypeId}" with value of variable "taskTypeId"
		When PUT request is submitted to "http://localhost:8999/api/task"
		Then Response status code should be 200
		
	Scenario: Get task
		When GET request is submitted to "http://localhost:8999/api/task/{{addedTaskId}}"
		Then Response status code should be 200
		
	Scenario: Get all tasks
		When GET request is submitted to "http://localhost:8999/api/task"
		Then Response status code should be 200
		
	Scenario: Delete task
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/deleteTask.json"
		And In request body template, replace "${addedTaskId}" with value of variable "addedTaskId"
		When DELETE request is submitted to "http://localhost:8999/api/task"
		Then Response status code should be 200
		
	Scenario: Get deleted task
		When GET request is submitted to "http://localhost:8999/api/task/{{addedTaskId}}"
		Then Response status code should be 410
		
	#Clean
	Scenario: Delete task type configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/deleteTaskTypeConfiguration.json"
		And In request body template, replace "${id}" with value of variable "taskTypeId"
		When DELETE request is submitted to "http://localhost:8999/api/configuration/taskType"
		Then Response status code should be 200
	
	Scenario: Delete task group configuration
		Given In request header, set "Content-Type" to "application/json"
		And Request body template is loaded from file "Task/deleteTaskGroupConfiguration.json"
		And In request body template, replace "${id}" with value of variable "taskGroupId"
		When DELETE request is submitted to "http://localhost:8999/api/configuration/taskGroup"
		Then Response status code should be 200