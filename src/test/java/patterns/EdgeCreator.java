package patterns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeCreator extends DriverCreator{
    @Override
    protected WebDriver createDriver() {
        return new EdgeDriver();
    }
}
