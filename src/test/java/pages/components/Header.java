package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By title = By.cssSelector("h1");

    public Header(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String titleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }
}
