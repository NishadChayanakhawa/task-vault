package io.github.nishadchayanakhawa.taskvault.tests.ui;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.nishadchayanakhawa.taskvault.model.Priority;
import io.github.nishadchayanakhawa.taskvault.tests.ui.pages.HomePage;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;
import io.restassured.RestAssured;

/**
 * TaskTypeConfigurationTests contains test cases to verify CRUD operations for
 * Task Type configurations in the application.
 */
class TaskTests {

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

	@Retry(3)
	@Test(priority = 1)
	void addTaskType() {
		TestFactory.recordTest("Pre-requisite: Add task type");
		RestAssured.given().contentType("application/json").body("""
				{
				    "name" : "Task Type 2"
				}
				""").when().put("http://localhost:8999/api/configuration/taskType").then().assertThat().statusCode(201);
	}

	@Retry(3)
	@Test(priority = 1)
	void addTaskGroup() {
		TestFactory.recordTest("Pre-requisite: Add task group");
		RestAssured.given().contentType("application/json").body("""
				{
				    "name" : "Task Group 2"
				}
				""").when().put("http://localhost:8999/api/configuration/taskGroup").then().assertThat()
				.statusCode(201);
	}

	@Retry(3)
	@Test(priority = 11, dependsOnMethods = { "addTaskType", "addTaskGroup" })
	void addRecord() {
		TestFactory.recordTest("Add task", homePage.getDriver());
		String addTaskToastMessage = homePage // home page
				.navigateToTaskManagement()// navigate to tage
				.addTask("Task Group 2", "Task Type 2", "Task #1", Priority.MEDIUM, null);// add task
		Assertions.assertThat(addTaskToastMessage).isEqualTo("Task 'Task #1' saved successfully");
	}

	@Retry(3)
	@Test(priority = 11, dependsOnMethods = { "addRecord" })
	void editRecord() {
		TestFactory.recordTest("Edit task", homePage.getDriver());
		String editTaskToastMessage = homePage // home page
				.navigateToTaskManagement()// navigate to tage
				.editTask("Task #1", "Task #1 Edited");
		Assertions.assertThat(editTaskToastMessage).isEqualTo("Task 'Task #1 Edited' saved successfully");
	}

	@Retry(3)
	@Test(priority = 21, dependsOnMethods = { "editRecord" })
	void deleteRecord() {
		TestFactory.recordTest("Delete task", homePage.getDriver());
		String deleteTaskToastMessage = homePage // home page
				.navigateToTaskManagement()// navigate to tage
				.deleteTask("Task #1 Edited");// delete task
		Assertions.assertThat(deleteTaskToastMessage).isEqualTo("Task 'Task #1 Edited' deleted successfully");
	}

	/**
	 * Cleans up the test environment after all test methods are executed. Closes
	 * the WebDriver instance.
	 */
	@AfterTest
	void tearDownTest() {
		homePage.getDriver().quit();
	}
}
