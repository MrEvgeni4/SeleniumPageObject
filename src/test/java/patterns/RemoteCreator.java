package patterns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteCreator extends DriverCreator {

    private final URL remoteUrl;

    public RemoteCreator(String remoteUrl, String browser) {
        try {
            this.remoteUrl = new URL(remoteUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Bad selenium.remote.url: " + remoteUrl, e);
        }
    }

    @Override
    protected WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        return new RemoteWebDriver(remoteUrl, options);
    }
}
