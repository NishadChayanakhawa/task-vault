package io.github.nishadchayanakhawa.taskvault.tests.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.github.nishadchayanakhawa.taskvault.model.Priority;

/**
 * TaskPage class represents the page for managing tasks in the application. It
 * extends BasePage to reuse common page functionalities.
 */
public class TaskPage extends BasePage {

	@FindBy(xpath = "//select[@id='taskTypeId']")
	WebElement taskType;

	@FindBy(xpath = "//select[@id='taskGroupId']")
	WebElement taskGroup;

	@FindBy(xpath = "//select[@id='priorityCode']")
	WebElement priority;

	@FindBy(xpath = "//input[@id='dueDate']")
	WebElement dueDate;

	/** WebElement for the name input field for tasks. */
	@FindBy(xpath = "//input[@id='name']")
	WebElement nameInput;

	/** WebElement for the Save Record button. */
	@FindBy(xpath = "//button[@id='saveRecordButton']")
	WebElement saveRecordButton;

	/**
	 * Constructor for TaskPage. It initializes the BasePage with the given
	 * WebDriver.
	 * 
	 * @param driver WebDriver instance used to interact with the page.
	 */
	protected TaskPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Adds a new task by filling in the name and saving the record.
	 * 
	 * @param name Name of the new task to be added.
	 * @return The toast message displayed after adding the task.
	 */
	public String addTask(String taskGroupVisibleText, String taskTypeVisibleText, String name, Priority taskPriority,
			String taskDueDate) {
		this.clickElement(addRecordButton, "Add record button"); // Click on Add Record button
		this.selectDropDownByVisibleText(taskGroup, taskGroupVisibleText);
		this.selectDropDownByVisibleText(taskType, taskTypeVisibleText);
		this.sendText(nameInput, name, "Task name input"); // Enter the task name
		this.selectDropDownByVisibleText(priority, taskPriority.getDisplayValue());
		this.sendText(dueDate, taskDueDate);
		this.clickElement(saveRecordButton, "Save record button"); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Edits an existing task by updating its name.
	 * 
	 * @param oldName The current name of the task to be edited.
	 * @param newName The new name to update for the task.
	 * @return The toast message displayed after editing the task.
	 */
	public String editTask(String oldName, String newName) {
		try {
			this.clickTaskEditButton(oldName); // Click on the Edit button for the specified task
		} catch (Exception e) {
			this.clickTaskEditButton(newName); // Click on the Edit button for the specified task
		}
		this.clearValue(nameInput); // Clear the current value in the name input field
		this.sendText(nameInput, newName, "Task name input"); // Enter the new name
		this.clickElement(saveRecordButton); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Deletes an existing task by its name.
	 * 
	 * @param name Name of the task to be deleted.
	 * @return The toast message displayed after deleting the task.
	 */
	public String deleteTask(String name) {
		this.clickTaskDeleteButton(name); // Click on the Delete button for the specified task
		this.clickElement(this.confirmDeleteOperationButton, "Confirm delete button"); // Confirm the delete operation
		return this.getToastMessage(); // Retrieve and return the toast message
	}

}
