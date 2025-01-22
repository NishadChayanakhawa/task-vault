package io.github.nishadchayanakhawa.taskvault.tests.ui;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.nishadchayanakhawa.taskvault.tests.ui.facades.TaskVaultFacades;
import io.github.nishadchayanakhawa.taskvault.tests.ui.pages.HomePage;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;

/**
 * TaskTypeConfigurationTests contains test cases to verify CRUD operations for
 * Task Type configurations in the application.
 */
class TaskTypeConfigurationTests {

	/** HomePage instance to interact with the application. */
	private static HomePage homePage;

	/**
	 * Sets up the test environment before any test method is executed. Initializes
	 * the HomePage instance.
	 */
	@BeforeClass
	void setupTest() {
		homePage = HomePage.getHomePage();
	}

	/**
	 * Cleans up the test environment after all test methods are executed. Closes
	 * the WebDriver instance.
	 */
	@AfterClass
	void tearDownTest() {
		homePage.getDriver().quit();
	}

	/**
	 * Test case to verify adding a new task type record. The test retries up to 3
	 * times if it fails.
	 */
	@Retry(3)
	@Test(priority = 11)
	void addRecord() {
		TestFactory.recordTest("Add test type record", homePage.getDriver());
		String addTaskTypeToastMessage = TaskVaultFacades.addTaskType(homePage, "Task type 1");
		Assertions.assertThat(addTaskTypeToastMessage) // Validate toast message
				.isEqualTo("Task type 'Task type 1' saved successfully"); // Compare with expected
	}

	/**
	 * Test case to verify the behavior when adding a duplicate task type record.
	 * Depends on the successful execution of the addRecord test. The test retries
	 * up to 3 times if it fails.
	 */
	@Retry(3)
	@Test(priority = 12, dependsOnMethods = { "addRecord" })
	void addDuplicateRecord() {
		TestFactory.recordTest("Add duplicate test type record", homePage.getDriver());
		String addTaskTypeToastMessage = homePage // Start transaction
				.navigateToTaskTypeConfiguration() // Navigate to task type configuration
				.addTaskType("Task type 1"); // Attempt to add duplicate task type
		Assertions.assertThat(addTaskTypeToastMessage) // Validate toast message
				.isEqualTo("Name 'Task type 1' already exists for Task Type"); // Compare with expected
	}

	/**
	 * Test case to verify editing an existing task type record. Depends on the
	 * successful execution of the addRecord test. The test retries up to 3 times if
	 * it fails.
	 */
	@Retry(3)
	@Test(priority = 13, dependsOnMethods = { "addRecord" })
	void editRecord() {
		TestFactory.recordTest("Edit test type record", homePage.getDriver());
		String addTaskTypeToastMessage = homePage // Start transaction
				.navigateToTaskTypeConfiguration() // Navigate to task type configuration
				.editTaskType("Task type 1", "New Task type 1"); // Edit task type record
		Assertions.assertThat(addTaskTypeToastMessage) // Validate toast message
				.isEqualTo("Task type 'New Task type 1' saved successfully"); // Compare with expected
	}

	/**
	 * Test case to verify deleting an existing task type record. Depends on the
	 * successful execution of the editRecord test. The test retries up to 3 times
	 * if it fails.
	 */
	@Retry(3)
	@Test(priority = 14, dependsOnMethods = { "editRecord" })
	void deleteRecord() {
		TestFactory.recordTest("Delete test type record", homePage.getDriver());
		String addTaskTypeToastMessage = homePage // Start transaction
				.navigateToTaskTypeConfiguration() // Navigate to task type configuration
				.deleteTaskType("New Task type 1"); // Delete task type record
		Assertions.assertThat(addTaskTypeToastMessage) // Validate toast message
				.isEqualTo("Task type 'New Task type 1' deleted successfully"); // Compare with expected
	}

}
