package io.github.nishadchayanakhawa.taskvault.tests.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * TaskTypeConfigurationPage class represents the page for managing task types
 * in the application. It extends BasePage to reuse common page functionalities.
 */
public class TaskTypeConfigurationPage extends BasePage {

	/** WebElement for the name input field for task types. */
	@FindBy(xpath = "//input[@id='name']")
	WebElement nameInput;

	/** WebElement for the Save Record button. */
	@FindBy(xpath = "//button[@id='saveRecordButton']")
	WebElement saveRecordButton;

	/**
	 * Constructor for TaskTypeConfigurationPage. It initializes the BasePage with
	 * the given WebDriver.
	 * 
	 * @param driver WebDriver instance used to interact with the page.
	 */
	protected TaskTypeConfigurationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Adds a new task type by filling in the name and saving the record.
	 * 
	 * @param name Name of the new task type to be added.
	 * @return The toast message displayed after adding the task type.
	 */
	public String addTaskType(String name) {
		this.clickElement(addRecordButton, "Add record button"); // Click on Add Record button
		this.sendText(nameInput, name, "Task type name input"); // Enter the task type name
		this.clickElement(saveRecordButton, "Save record button"); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Edits an existing task type by updating its name.
	 * 
	 * @param oldName The current name of the task type to be edited.
	 * @param newName The new name to update for the task type.
	 * @return The toast message displayed after editing the task type.
	 */
	public String editTaskType(String oldName, String newName) {
		try {
			this.clickEditButton(oldName); // Click on the Edit button for the specified task type
		}catch(Exception e) {
			this.clickEditButton(newName); // Click on the Edit button for the specified task type
		}
		this.clearValue(nameInput); // Clear the current value in the name input field
		this.sendText(nameInput, newName, "Task type name input"); // Enter the new name
		this.clickElement(saveRecordButton); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Deletes an existing task type by its name.
	 * 
	 * @param name Name of the task type to be deleted.
	 * @return The toast message displayed after deleting the task type.
	 */
	public String deleteTaskType(String name) {
		this.clickDeleteButton(name); // Click on the Delete button for the specified task type
		this.clickElement(this.confirmDeleteOperationButton, "Confirm delete button"); // Confirm the delete operation
		return this.getToastMessage(); // Retrieve and return the toast message
	}

}
