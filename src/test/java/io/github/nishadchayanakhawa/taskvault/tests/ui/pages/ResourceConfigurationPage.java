package io.github.nishadchayanakhawa.taskvault.tests.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * ResourceConfigurationPage class represents the page for managing resources
 * in the application. It extends BasePage to reuse common page functionalities.
 */
public class ResourceConfigurationPage extends BasePage {

	/** WebElement for the name input field for resources. */
	@FindBy(xpath = "//input[@id='name']")
	WebElement nameInput;

	/** WebElement for the Save Record button. */
	@FindBy(xpath = "//button[@id='saveRecordButton']")
	WebElement saveRecordButton;

	/**
	 * Constructor for ResourceConfigurationPage. It initializes the BasePage with
	 * the given WebDriver.
	 * 
	 * @param driver WebDriver instance used to interact with the page.
	 */
	protected ResourceConfigurationPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Adds a new resource by filling in the name and saving the record.
	 * 
	 * @param name Name of the new resource to be added.
	 * @return The toast message displayed after adding the resource.
	 */
	public String addResource(String name) {
		this.clickElement(addRecordButton, "Add record button"); // Click on Add Record button
		this.sendText(nameInput, name, "Resource name input"); // Enter the resource name
		this.clickElement(saveRecordButton, "Save record button"); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Edits an existing resource by updating its name.
	 * 
	 * @param oldName The current name of the resource to be edited.
	 * @param newName The new name to update for the resource.
	 * @return The toast message displayed after editing the resource.
	 */
	public String editResource(String oldName, String newName) {
		try {
			this.clickEditButton(oldName); // Click on the Edit button for the specified resource
		}catch(Exception e) {
			this.clickEditButton(newName); // Click on the Edit button for the specified resource
		}
		this.clearValue(nameInput); // Clear the current value in the name input field
		this.sendText(nameInput, newName, "Resource name input"); // Enter the new name
		this.clickElement(saveRecordButton); // Click on Save button
		return this.getToastMessage(); // Retrieve and return the toast message
	}

	/**
	 * Deletes an existing resource by its name.
	 * 
	 * @param name Name of the resource to be deleted.
	 * @return The toast message displayed after deleting the resource.
	 */
	public String deleteResource(String name) {
		this.clickDeleteButton(name); // Click on the Delete button for the specified resource
		this.clickElement(this.confirmDeleteOperationButton, "Confirm delete button"); // Confirm the delete operation
		return this.getToastMessage(); // Retrieve and return the toast message
	}

}
