package Utilities;

import enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFabric {
    public static WebDriver getDriver (BrowserType browserType){
        return switch (browserType){
            case EDGE -> getEdgeDriver();
            case FIREFOX -> getFirefoxDriver();
            case CHROME -> getChromeDriver();
            default -> getChromeDriver();
        };
    }

    private static ChromeDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("window-size=1400,1000");
        return new ChromeDriver (options);
    }

    private static FirefoxDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        // options to run FireFox headless
        options.addArguments ("--headless");
        options.addArguments("--width=1400");
        options.addArguments("--height=1000");
        return new FirefoxDriver(options);
    }

    private static EdgeDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
