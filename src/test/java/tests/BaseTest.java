package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import patterns.WebDriverFactory;

public class BaseTest {
    protected WebDriver driver;
    protected final String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java";

    @BeforeEach
    void setup() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createWebDriver(browser);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
