package patterns;

import org.openqa.selenium.WebDriver;

public abstract class DriverCreator {

    public WebDriver create() {
        WebDriver driver = createDriver();
        driver.manage().window().maximize();
        return driver;
    }

    protected abstract WebDriver createDriver();
}
