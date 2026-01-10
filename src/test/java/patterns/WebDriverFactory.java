package patterns;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public static WebDriver createWebDriver(String browser) {

        DriverCreator creator = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeCreator();
            case "edge" -> new EdgeCreator();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
        return creator.create();
    }
}
