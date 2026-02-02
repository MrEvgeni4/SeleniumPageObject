package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footer {
    private final WebDriverWait wait;

    private final By authorLink = By.cssSelector("footer a[href*='bonigarcia.dev']");

    public Footer(WebDriverWait wait) {
        this.wait = wait;
    }

    public String authorHref() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(authorLink)).getAttribute("href");
    }
}
