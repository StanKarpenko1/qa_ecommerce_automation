package tests;

import Utilities.BrowserFabric;
import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;


public class BaseTest {
    protected WebDriver driver;
    protected String url;
    protected String username;
    protected String password;
    protected MainPage mainPage;
    protected String firstName;
    protected String lastName;
    protected String zipCode;


    @Parameters({"browser", "url", "username", "password"})// Parameters are set in a xml file.

    @BeforeMethod // run before each test case, here setting up the WebDriver
    public void startUp (String browser, String url, String username, String password){
       // Set the location of the chromedriver executable
        BrowserType type = browser.equals("Chrome") ? BrowserType.CHROME :
                browser.equals("Firefox") ? BrowserType.FIREFOX :
                        browser.equals("Edge") ? BrowserType.EDGE : BrowserType.CHROME;
        driver = BrowserFabric.getDriver(type);
        mainPage = new MainPage(driver);

        this.url = url;
        this.username = username;
        this.password = password;
        driver.get(url);

        // login sequence
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        MainPage mainPage = loginPage.loginToApp(username, password);
        Assert.assertTrue(mainPage.isOpen());

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if(driver!=null){driver.quit();}
    }

//    public MainPage login(){
//
//    }
}


