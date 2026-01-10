package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.WebFormPage;
import pages.LoginFormPage;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageTests extends BaseTest {

    private MainPage main;

    @BeforeEach
    void openWebForm() {
        main = new MainPage(driver, baseUrl).open();
    }

    @Test
    void headerAndFooterShouldContainExpectedText() {
        assertEquals("Hands-On Selenium WebDriver with Java", main.header().titleText());
        assertEquals("Boni García", main.footer().authorText());
        assertTrue(main.footer().authorHref().contains("bonigarcia.dev"));
    }

    @Test
    void goToWebForm() {
        WebFormPage webForm = main.goToWebForm();
        assertEquals("Web form", webForm.titleWebText());
    }

    @Test
    void goToLoginForm() {
        LoginFormPage loginForm = main.goToLoginForm();
        assertEquals("Login form", loginForm.titleLoginText());
    }
}
