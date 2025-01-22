package io.github.nishadchayanakhawa.taskvault.tests.ui;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.nishadchayanakhawa.taskvault.tests.ui.pages.HomePage;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;

/**
 * TaskGroupConfigurationTests contains test cases to verify CRUD operations for
 * Task Group configurations in the application.
 */
class TaskGroupConfigurationTests {

	/** HomePage instance to interact with the application. */
	private static HomePage homePage;

	/**
	 * Sets up the test environment before any test method is executed. Initializes
	 * the HomePage instance.
	 */
	@BeforeTest
	void setupTest() {
		homePage = HomePage.getHomePage();
	}

	/**
	 * Cleans up the test environment after all test methods are executed. Closes
	 * the WebDriver instance.
	 */
	@AfterTest
	void tearDownTest() {
		homePage.getDriver().quit();
	}

	/**
	 * Test case to verify adding a new task group record. The test retries up to 3
	 * times if it fails.
	 */
	@Retry(3)
	@Test(priority = 11)
	void addRecord() {
		TestFactory.recordTest("Add test group record", homePage.getDriver());
		String addTaskGroupToastMessage = homePage // Start transaction
				.navigateToTaskGroupConfiguration() // Navigate to task group configuration
				.addTaskGroup("Task group 1"); // Add task group
		Assertions.assertThat(addTaskGroupToastMessage) // Validate toast message
				.isEqualTo("Task group 'Task group 1' saved successfully"); // Compare with expected
	}

	/**
	 * Test case to verify the behavior when adding a duplicate task group record.
	 * Depends on the successful execution of the addRecord test. The test retries
	 * up to 3 times if it fails.
	 */
	@Retry(3)
	@Test(priority = 12, dependsOnMethods = { "addRecord" })
	void addDuplicateRecord() {
		TestFactory.recordTest("Add duplicate test group record", homePage.getDriver());
		String addTaskGroupToastMessage = homePage // Start transaction
				.navigateToTaskGroupConfiguration() // Navigate to task group configuration
				.addTaskGroup("Task group 1"); // Attempt to add duplicate task group
		Assertions.assertThat(addTaskGroupToastMessage) // Validate toast message
				.isEqualTo("Name 'Task group 1' already exists for Task Group"); // Compare with expected
	}

	/**
	 * Test case to verify editing an existing task group record. Depends on the
	 * successful execution of the addRecord test. The test retries up to 3 times if
	 * it fails.
	 */
	@Retry(3)
	@Test(priority = 13, dependsOnMethods = { "addRecord" })
	void editRecord() {
		TestFactory.recordTest("Edit test group record", homePage.getDriver());
		String addTaskGroupToastMessage = homePage // Start transaction
				.navigateToTaskGroupConfiguration() // Navigate to task group configuration
				.editTaskGroup("Task group 1", "New Task group 1"); // Edit task group record
		Assertions.assertThat(addTaskGroupToastMessage) // Validate toast message
				.isEqualTo("Task group 'New Task group 1' saved successfully"); // Compare with expected
	}

	/**
	 * Test case to verify deleting an existing task group record. Depends on the
	 * successful execution of the editRecord test. The test retries up to 3 times
	 * if it fails.
	 */
	@Retry(3)
	@Test(priority = 14, dependsOnMethods = { "editRecord" })
	void deleteRecord() {
		TestFactory.recordTest("Delete test group record", homePage.getDriver());
		String addTaskGroupToastMessage = homePage // Start transaction
				.navigateToTaskGroupConfiguration() // Navigate to task group configuration
				.deleteTaskGroup("New Task group 1"); // Delete task group record
		Assertions.assertThat(addTaskGroupToastMessage) // Validate toast message
				.isEqualTo("Task group 'New Task group 1' deleted successfully"); // Compare with expected
	}

}
