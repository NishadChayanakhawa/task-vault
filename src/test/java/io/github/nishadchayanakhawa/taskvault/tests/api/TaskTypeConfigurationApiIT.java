package io.github.nishadchayanakhawa.taskvault.tests.api;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Test class for TaskType configuration API integration tests using Cucumber
 * and TestNG.
 * <p>
 * This class is responsible for running the integration tests defined in
 * Cucumber feature files. It uses the CucumberOptions annotation to specify the
 * location of feature files and step definitions. The integration tests cover
 * the API endpoints related to TaskType configuration.
 * </p>
 */
@CucumberOptions(
		// Specifies the location of the Cucumber feature files that contain the API
		// test scenarios.
		features = "src/test/resources/features/TaskTypeConfigurationApiTests.feature",

		// Specifies the package where the step definition methods are implemented for
		// the tests.
		glue = "io.nishadc.automationtestingframework.testinginterface.restapi.stepdefinitions")
public class TaskTypeConfigurationApiIT extends AbstractTestNGCucumberTests {
	// This class extends AbstractTestNGCucumberTests to integrate Cucumber with
	// TestNG and run the tests.
}
