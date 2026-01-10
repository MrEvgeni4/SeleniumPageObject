package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final String baseUrl;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        PageFactory.initElements(driver, this);
    }

    protected WebElement locator(By locator) {
        return driver.findElement(locator);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected String textOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected void type(WebElement element, String text) {
        WebElement e = wait.until(ExpectedConditions.visibilityOf(element));
        e.sendKeys(text);
    }

    protected boolean isVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
