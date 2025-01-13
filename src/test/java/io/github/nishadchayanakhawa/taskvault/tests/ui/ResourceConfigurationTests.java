package io.github.nishadchayanakhawa.taskvault.tests.ui;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.nishadchayanakhawa.taskvault.tests.ui.pages.HomePage;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;
import io.nishadc.automationtestingframework.testngcustomization.annotations.Retry;

/**
 * ResourceConfigurationTests contains test cases to verify CRUD operations for
 * Task Group configurations in the application.
 */
class ResourceConfigurationTests {

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
	 * Test case to verify adding a new resource record. The test retries up to 3
	 * times if it fails.
	 */
	@Retry(3)
	@Test(priority = 11)
	void addRecord() {
		TestFactory.recordTest("Add resource record", homePage.getDriver());
		String addResourceToastMessage = homePage // Start transaction
				.navigateToResourceConfiguration() // Navigate to resource configuration
				.addResource("Resource 1"); // Add resource
		Assertions.assertThat(addResourceToastMessage) // Validate toast message
				.isEqualTo("Resource 'Resource 1' saved successfully"); // Compare with expected
	}

	/**
	 * Test case to verify the behavior when adding a duplicate resource record.
	 * Depends on the successful execution of the addRecord test. The test retries
	 * up to 3 times if it fails.
	 */
	@Retry(3)
	@Test(priority = 12, dependsOnMethods = { "addRecord" })
	void addDuplicateRecord() {
		TestFactory.recordTest("Add duplicate resource record", homePage.getDriver());
		String addResourceToastMessage = homePage // Start transaction
				.navigateToResourceConfiguration() // Navigate to resource configuration
				.addResource("Resource 1"); // Attempt to add duplicate resource
		Assertions.assertThat(addResourceToastMessage) // Validate toast message
				.isEqualTo("Name 'Resource 1' already exists for Task Group"); // Compare with expected
	}

	/**
	 * Test case to verify editing an existing resource record. Depends on the
	 * successful execution of the addRecord test. The test retries up to 3 times if
	 * it fails.
	 */
	@Retry(3)
	@Test(priority = 13, dependsOnMethods = { "addRecord" })
	void editRecord() {
		TestFactory.recordTest("Edit resource record", homePage.getDriver());
		String addResourceToastMessage = homePage // Start transaction
				.navigateToResourceConfiguration() // Navigate to resource configuration
				.editResource("Resource 1", "New Resource 1"); // Edit resource record
		Assertions.assertThat(addResourceToastMessage) // Validate toast message
				.isEqualTo("Resource 'New Resource 1' saved successfully"); // Compare with expected
	}

	/**
	 * Test case to verify deleting an existing resource record. Depends on the
	 * successful execution of the editRecord test. The test retries up to 3 times
	 * if it fails.
	 */
	@Retry(3)
	@Test(priority = 14, dependsOnMethods = { "editRecord" })
	void deleteRecord() {
		TestFactory.recordTest("Delete resource record", homePage.getDriver());
		String addResourceToastMessage = homePage // Start transaction
				.navigateToResourceConfiguration() // Navigate to resource configuration
				.deleteResource("New Resource 1"); // Delete resource record
		Assertions.assertThat(addResourceToastMessage) // Validate toast message
				.isEqualTo("Resource 'New Resource 1' deleted successfully"); // Compare with expected
	}

}
