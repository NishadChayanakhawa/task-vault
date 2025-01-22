package io.github.nishadchayanakhawa.taskvault.tests.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * HomePage class extends the BasePage and represents the homepage of the
 * application. It provides methods to initialize the WebDriver and navigate to
 * the home page.
 */
public class HomePage extends BasePage {

	/**
	 * Creates and returns an instance of HomePage. This method initializes the
	 * WebDriver with ChromeOptions configured for headless mode, maximized window
	 * size, and specific arguments for remote origins.
	 * 
	 * @return HomePage instance
	 */
	public static HomePage getHomePage(boolean isHeadless) {
		// Suppress Selenium logs
		java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

		// Configure ChromeOptions for WebDriver
		ChromeOptions options = new ChromeOptions();
		if (isHeadless) {
			options.addArguments("--headless=new"); // Run browser in headless mode
		}
		options.addArguments("--remote-allow-origins=*"); // Allow cross-origin requests
		options.addArguments("start-maximized"); // Start browser maximized
		options.addArguments("--window-size=1920,1080"); // Set default window size

		// Create WebDriver instance
		WebDriver driver = WebDriverManager.chromedriver().capabilities(options).create();

		// Navigate to the home page
		driver.get("http://localhost:8999/home");

		// Return a new HomePage instance
		return new HomePage(driver);
	}

	public static HomePage getHomePage() {
		return HomePage.getHomePage(true);
	}

	/**
	 * Constructor for HomePage. It initializes the BasePage with the given
	 * WebDriver.
	 * 
	 * @param driver WebDriver instance to interact with the browser
	 */
	protected HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Provides access to the WebDriver instance associated with this page.
	 * 
	 * @return WebDriver instance
	 */
	public WebDriver getDriver() {
		return this.driver;
	}

}
