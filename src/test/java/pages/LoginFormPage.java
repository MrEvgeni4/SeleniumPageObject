package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginFormPage extends BasePage{

    @FindBy(css = "h1.display-6")
    private WebElement titleLogin;

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = "button[type='submit']")
    private WebElement submit;

    @FindBy(id = "invalid")
    private WebElement invalidAlert;

    public LoginFormPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public LoginFormPage open() {
        driver.get(baseUrl + "/login-form.html");

        wait.until(ExpectedConditions.urlContains("login-form.html"));
        wait.until(ExpectedConditions.visibilityOf(titleLogin));

        return this;
    }

    public String titleLoginText() {
        return textOf(titleLogin);
    }

    public String usernameType() {
        return username.getAttribute("type");
    }

    public String passwordType() {
        return password.getAttribute("type");
    }

    public String submitType() {
        return submit.getAttribute("type");
    }

    public LoginSuccessPage loginValid(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(submit);

        wait.until(ExpectedConditions.urlContains("login-sucess.html"));
        return new LoginSuccessPage(driver, baseUrl);
    }

    public LoginFormPage loginInvalid(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(submit);

        wait.until(ExpectedConditions.visibilityOf(invalidAlert));
        return this;
    }

    public String invalidText() {
        return textOf(invalidAlert);
    }

}
