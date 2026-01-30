package patterns;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public static WebDriver createWebDriver(String browser) {

        String remoteUrl = System.getProperty("selenium.remote.url");
        if (remoteUrl == null || remoteUrl.isBlank()) {
            remoteUrl = System.getenv("SELENIUM_REMOTE_URL");
        }

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            return new RemoteCreator(remoteUrl).create();
        }

        DriverCreator creator = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeCreator();
            case "edge" -> new EdgeCreator();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
        return creator.create();
    }
}
