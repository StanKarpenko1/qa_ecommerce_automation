package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static constants.Constants.TimeoutVariables.EXPLICIT_WAIT;
import static constants.Constants.TimeoutVariables.POLLING_INTERVAL;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT), Duration.ofMillis((POLLING_INTERVAL)));
    }
}
