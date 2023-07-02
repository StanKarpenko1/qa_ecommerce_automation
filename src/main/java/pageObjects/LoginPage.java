package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    private WebElement getErrorFrame(){
        By errorLocator = By.cssSelector(".error-message-container.error");
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
        return driver.findElement(errorLocator);
    }

    public boolean isError() {
        try {
            WebElement error = getErrorFrame();
            return error.isDisplayed();
        } catch (TimeoutException err){
            return false;
        }

    }
}
