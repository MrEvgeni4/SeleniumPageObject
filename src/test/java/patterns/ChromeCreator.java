package patterns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeCreator extends DriverCreator {
    @Override
    protected WebDriver createDriver() {
        return new ChromeDriver();
    }
}
