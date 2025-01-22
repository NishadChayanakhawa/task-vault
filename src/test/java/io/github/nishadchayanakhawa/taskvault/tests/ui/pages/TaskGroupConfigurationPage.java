package io.github.nishadchayanakhawa.taskvault.tests.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * TaskGroupConfigurationPage class represents the page for managing task groups
 * in the application. It extends BasePage to reuse common page functionalities.
 */
public class TaskGroupConfigurationPage extends BasePage {

	/** WebElement for the name input field for task groups. */
	@FindBy(xpath = "//input[@id='name']")
	WebElement nameInput;

	/** WebElement for the Save Record button. */
	@FindBy(xpath = "//button[@id='saveRecordButton']")
	WebElement saveRecordButton;

	/**
	 * Constructor for TaskGroupConfigurationPage. It initializes the BasePage with
	 * the given WebDriver.
	 * 
	 * @param driver WebDriver instance used to interact with the page.
	 */
	protected TaskGroupConfigurationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Adds a new task group by filling in the name and saving the record.
	 * 
	 * @param name Name of the new task group to be added.
	 * @return The toast message displayed after adding the task group.
	 */
	public String addTaskGroup(String name) {
		this.clickElement(addRecordButton, "Add record button"); // Click on Add Record button
		this.sendText(nameInput, name, "Task group name input"); // Enter the task group name
		this.clickElement(saveRecordButton, "Save record button"); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Edits an existing task group by updating its name.
	 * 
	 * @param oldName The current name of the task group to be edited.
	 * @param newName The new name to update for the task group.
	 * @return The toast message displayed after editing the task group.
	 */
	public String editTaskGroup(String oldName, String newName) {
		try {
			this.clickEditButton(oldName); // Click on the Edit button for the specified task group
		}catch(Exception e) {
			this.clickEditButton(newName); // Click on the Edit button for the specified task group
		}
		this.clearValue(nameInput); // Clear the current value in the name input field
		this.sendText(nameInput, newName, "Task group name input"); // Enter the new name
		this.clickElement(saveRecordButton); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Deletes an existing task group by its name.
	 * 
	 * @param name Name of the task group to be deleted.
	 * @return The toast message displayed after deleting the task group.
	 */
	public String deleteTaskGroup(String name) {
		this.clickDeleteButton(name); // Click on the Delete button for the specified task group
		this.clickElement(this.confirmDeleteOperationButton, "Confirm delete button"); // Confirm the delete operation
		return this.getToastMessage(); // Retrieve and return the toast message
	}

}
