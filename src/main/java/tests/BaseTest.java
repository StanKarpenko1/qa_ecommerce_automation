package tests;

import Utilities.BrowserFabric;
import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.MainPage;


public class BaseTest {
    protected WebDriver driver;
    protected String url;
    protected String username;
    protected String password;

    @Parameters({"browser", "url", "username", "password"})// Parameters are set in a xml file.

    @BeforeMethod // run before each test case, here setting up the WebDriver
    public void startUp (String browser, String url, String username, String password){
       // Set the location of the chromedriver executable
        BrowserType type = browser.equals("Chrome") ? BrowserType.CHROME :
                browser.equals("Firefox") ? BrowserType.FIREFOX :
                        browser.equals("Edge") ? BrowserType.EDGE : BrowserType.CHROME;
        driver = BrowserFabric.getDriver(type);

        this.url = url;
        this.username = username;
        this.password = password;
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        if(driver!=null){driver.quit();}
    }

//    public MainPage login(){
//
//    }
}


