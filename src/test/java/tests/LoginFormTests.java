package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginFormPage;
import pages.LoginSuccessPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginFormTests extends BaseTest{

    private LoginFormPage page;

    @BeforeEach
    void openLoginForm() {
        page = new LoginFormPage(driver, baseUrl).open();
    }

    @Test
    void titleShouldBeLoginForm() {
        assertEquals("Login form", page.titleLoginText());
    }

    @Test
    void passwordFieldShouldHaveTypePassword() {
        assertEquals("password", page.passwordType());
    }

    @Test
    void submitButtonShouldHaveTypeSubmit() {
        assertEquals("submit", page.submitType());
    }

    @Test
    void validCredentialsShouldNavigateToSuccessPage() {
        LoginSuccessPage success = page.loginValid(validLogin, validPassword);

        assertEquals("Login form", success.titleSuccessText());
        assertTrue(success.successText().contains("Login successful"));
    }

    @Test
    void invalidCredentialsShouldShowInvalidMessage() {
        page.loginInvalid("test", "test");

        assertFalse(driver.getCurrentUrl().contains("login-sucess.html"));
        assertEquals("Invalid credentials", page.invalidText());
    }
}
