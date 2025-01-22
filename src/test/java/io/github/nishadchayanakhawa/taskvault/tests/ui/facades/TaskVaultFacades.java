package io.github.nishadchayanakhawa.taskvault.tests.ui.facades;

import io.github.nishadchayanakhawa.taskvault.tests.ui.pages.HomePage;

public class TaskVaultFacades {
	public static String addTaskType(HomePage homePage, String taskTypeName) {
		return homePage // Start transaction
				.navigateToTaskTypeConfiguration() // Navigate to task type configuration
				.addTaskType(taskTypeName); // Add task type
	}

	public static String addTaskGroup(HomePage homePage, String taskGroupName) {
		return homePage // Start transaction
				.navigateToTaskGroupConfiguration() // Navigate to task group configuration
				.addTaskGroup(taskGroupName); // Add task type
	}
}
