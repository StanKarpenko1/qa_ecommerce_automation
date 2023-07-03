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

    public void clearCredentials (){
        getUsernameField().clear();
        getPasswordField().clear();
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
        } catch (TimeoutException err) {
            return false;
        }
    }

    private WebElement getUsernameRequiredError (){
        By usernameRequiredError = By.xpath("//*[text() = 'Epic sadface: Username is required']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameRequiredError));
        return driver.findElement(usernameRequiredError);
    }

    public boolean isUsernameRequiredError(){
        try {
            WebElement usernameRequiredError = getUsernameRequiredError();
            return usernameRequiredError.isDisplayed();
        } catch (TimeoutException err){
            return false;
        }
    }
    private WebElement getPaseRequiredError (){
        By passRequiredError = By.xpath("//*[text() = 'Epic sadface: Password is required']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passRequiredError));
        return driver.findElement(passRequiredError);
    }
    public boolean isPassRequiredError(){
        try {
            WebElement passRequiredError = getPaseRequiredError();
            return passRequiredError.isDisplayed();
        } catch (TimeoutException err){
            return false;
        }
    }
}
