package tests;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import patterns.WebDriverFactory;

@ExtendWith(AllureJunit5.class)
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

    protected String appLogin() {
        return required("app.login", "APP_LOGIN");
    }

    protected String appPassword() {
        return required("app.password", "APP_PASSWORD");
    }

    private static String required(String sysKey, String envKey) {
        String v = System.getProperty(sysKey);
        if (v == null || v.isBlank()) v = System.getenv(envKey);
        if (v == null || v.isBlank()) {
            throw new IllegalStateException(
                    "Not specified " + sysKey + ". Pass it on -D" + sysKey + "=... or set an environment variable " + envKey
            );
        }
        return v;
    }
}
