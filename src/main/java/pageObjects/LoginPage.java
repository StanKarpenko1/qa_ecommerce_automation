package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.*;

public class LoginPage extends BasePage{
    public LoginPage (WebDriver driver) {
        super(driver);
    }

    private WebElement getUsernameField () {return driver.findElement(By.xpath("//*[@id='user-name']"));}
    private WebElement getPasswordField () {return driver.findElement(By.id("password"));}
    private WebElement getLoginButton () {return driver.findElement(By.id("login-button"));}

    public void open(String url) {
        driver.get(url);
    }

    public MainPage loginToApp(String username, String password) {
        getUsernameField().sendKeys(username);
        getPasswordField().sendKeys(password);
        getLoginButton().click();

        return new MainPage(driver);
    }
};
