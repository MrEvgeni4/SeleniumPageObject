package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubmittedFormPage extends BasePage{

    @FindBy(css = "h1.display-6")
    private WebElement title;

    public SubmittedFormPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
        waitUntilLoaded();
    }

    private void waitUntilLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("submitted-form.html"));
        wait.until(ExpectedConditions.visibilityOf(title));
    }

    public String titleText() {
        return textOf(title);
    }
}
