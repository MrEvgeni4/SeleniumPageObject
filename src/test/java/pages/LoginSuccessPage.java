package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginSuccessPage extends BasePage {
    @FindBy(css = "h1.display-6")
    private WebElement titleSuccessLogin;

    @FindBy(id = "success")
    private WebElement successAlert;

    public LoginSuccessPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public String titleSuccessText() {
        return textOf(titleSuccessLogin);
    }

    public String successText() {
        return textOf(successAlert);
    }
}
