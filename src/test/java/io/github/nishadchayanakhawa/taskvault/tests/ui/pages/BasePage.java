package io.github.nishadchayanakhawa.taskvault.tests.ui.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.nishadc.automationtestingframework.testinginterface.webui.ApplicationActions;

/**
 * BasePage class provides common functionality and elements for pages in the
 * application. It extends ApplicationActions to utilize utility methods for web
 * interactions.
 */
public class BasePage extends ApplicationActions {

	/** WebElement for the Configurations link on the page. */
	@FindBy(xpath = "//a[@id='configurationsLink']")
	WebElement configurationsLink;

	/** WebElement for the Task Type option under Configurations. */
	@FindBy(xpath = "//a[@id='taskTypeOption']")
	WebElement taskTypeOption;

	/** WebElement for the Task Type option under Configurations. */
	@FindBy(xpath = "//a[@id='taskGroupOption']")
	WebElement taskGroupOption;

	/** WebElement for the Add Record button. */
	@FindBy(xpath = "//button[@id='addRecordButton']")
	WebElement addRecordButton;

	/** WebElement for the Save Record button. */
	@FindBy(xpath = "//button[@id='saveRecordButton']")
	WebElement saveRecordButton;

	/** WebElement for the Confirm Delete Operation button. */
	@FindBy(xpath = "//button[@id='confirmDeleteOperation']")
	WebElement confirmDeleteOperationButton;

	/** WebElement for the Dismiss Modal button. */
	@FindBy(xpath = "//button[@id='dismissModal']")
	WebElement dismissModalButton;

	/** WebElement for the visible toast message. */
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div/div[2]")
	WebElement visibleToastMessage;

	/** WebElement for the Close button of the toast message. */
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div/button")
	WebElement closeToastMessage;

	/** Template XPath for dynamically locating Edit buttons by record name. */
	private static final String EDIT_BUTTON_XPATH_TEMPLATE = "//td[text()='<RECORD_NAME>']/parent::tr//button[starts-with(@id,'editRecordButton_')]";

	/** Template XPath for dynamically locating Delete buttons by record name. */
	private static final String DELETE_BUTTON_XPATH_TEMPLATE = "//td[text()='<RECORD_NAME>']/parent::tr//button[starts-with(@id,'deleteRecordButton_')]";

	/** Placeholder used in XPath templates to dynamically replace record names. */
	private static final String RECORD_NAME_PLACEHOLDER = "<RECORD_NAME>";

	/**
	 * Clicks the Edit button corresponding to the specified record name.
	 * 
	 * @param recordName Name of the record for which the Edit button should be
	 *                   clicked.
	 */
	public void clickEditButton(String recordName) {
		this.clickElement(BasePage.EDIT_BUTTON_XPATH_TEMPLATE.replace(BasePage.RECORD_NAME_PLACEHOLDER, recordName));
	}

	/**
	 * Clicks the Delete button corresponding to the specified record name.
	 * 
	 * @param recordName Name of the record for which the Delete button should be
	 *                   clicked.
	 */
	public void clickDeleteButton(String recordName) {
		this.clickElement(BasePage.DELETE_BUTTON_XPATH_TEMPLATE.replace(BasePage.RECORD_NAME_PLACEHOLDER, recordName));
	}

	/**
	 * Constructor for BasePage. It initializes the PageFactory elements and sets up
	 * the WebDriver instance.
	 * 
	 * @param driver WebDriver instance used for interacting with the page.
	 */
	protected BasePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Navigates to the Task Type Configuration page through the Configurations
	 * menu.
	 * 
	 * @return An instance of TaskTypeConfigurationPage.
	 */
	public TaskTypeConfigurationPage navigateToTaskTypeConfiguration() {
		this.clickElement(configurationsLink, "Configurations");
		this.clickElement(taskTypeOption, "Task Type option");
		return new TaskTypeConfigurationPage(this.driver);
	}

	/**
	 * Navigates to the Task Type Configuration page through the Configurations
	 * menu.
	 * 
	 * @return An instance of TaskGroupConfigurationPage.
	 */
	public TaskGroupConfigurationPage navigateToTaskGroupConfiguration() {
		this.clickElement(configurationsLink, "Configurations");
		this.clickElement(taskGroupOption, "Task Group option");
		return new TaskGroupConfigurationPage(this.driver);
	}

	/**
	 * Retrieves the visible toast message, closes it, and handles any modal
	 * dismissals if present.
	 * 
	 * @return The text of the visible toast message.
	 */
	public String getToastMessage() {
		String toastMessage = this.getInnerText(visibleToastMessage);
		this.clickElement(closeToastMessage);
		try {
			if (this.isDisplayed(dismissModalButton)) {
				this.clickElement(dismissModalButton);
			}
		} catch (NoSuchElementException e) {
			// No modal to dismiss, ignore exception.
		}
		return toastMessage;
	}
}
