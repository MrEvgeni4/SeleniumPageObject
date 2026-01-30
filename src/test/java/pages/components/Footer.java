package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footer {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By authorLink = By.xpath("//a[text()='Boni García']");

    public Footer(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String authorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(authorLink)).getText().trim();
    }

    public String authorHref() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(authorLink)).getAttribute("href");
    }
}
