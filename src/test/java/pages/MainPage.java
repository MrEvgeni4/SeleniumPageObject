package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.Footer;
import pages.components.Header;

public class MainPage extends BasePage{

    @FindBy(css = "[href='web-form.html']")
    private WebElement webFormLink;

    @FindBy(css = "[href='login-form.html']")
    private WebElement loginFormLink;

    public MainPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public MainPage open() {
        driver.get(baseUrl + "/index.html");
        return this;
    }

    public WebFormPage goToWebForm() {
        click(webFormLink);
        return new WebFormPage(driver, baseUrl);
    }

    public LoginFormPage goToLoginForm() {
        click(loginFormLink);
        return new LoginFormPage(driver, baseUrl);
    }

    public Header header() {
        return new Header(driver, wait);
    }

    public Footer footer() {
        return new Footer(driver, wait);
    }
}
