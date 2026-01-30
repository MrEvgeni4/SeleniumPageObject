package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import patterns.WebDriverFactory;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AllureJunit5.class)
public class BaseTest {
    protected WebDriver driver;
    protected final String baseUrl = System.getProperty(
            "base.url",
            System.getenv().getOrDefault("BASE_URL", "https://bonigarcia.dev/selenium-webdriver-java"));
    protected final String validLogin = System.getProperty("app.login",
            System.getenv().getOrDefault("APP_LOGIN", "user"));
    protected final String validPassword = System.getProperty("app.password",
            System.getenv().getOrDefault("APP_PASSWORD", "user"));

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
