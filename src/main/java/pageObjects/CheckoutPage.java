package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.BaseTest;

public class CheckoutPage extends BasePage {
    public CheckoutPage (WebDriver driver) {super(driver);}

    public boolean isOpen(){
        try {
            return getFirstNameField().isDisplayed();
        } catch (TimeoutException eeee){
            return false;
        }
    }

    public WebElement getFirstNameField() {
        By firstNameLocator = By.id("first-name");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameLocator));
        return driver.findElement(firstNameLocator);
    }
    public WebElement getLastNameField() {
        By lastNameLocator = By.id("last-name");
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameLocator));
        return driver.findElement(lastNameLocator);
    }
    public WebElement getZipField() {
        By zipLocator = By.id("postal-code");
        wait.until(ExpectedConditions.visibilityOfElementLocated(zipLocator));
        return driver.findElement(zipLocator);
    }
    public String getFirstNameFieldValue () {
        return  getFirstNameField().getAttribute("value");
    }
    public String getLastNameFieldValue() {
        return getLastNameField().getAttribute("value");
    }
    public String getZipFieldValue() {
        return getZipField().getAttribute("value");
    }

    // continue button
    public WebElement getContinueButton() {
        By continueBtnSelector = By.id("continue");
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtnSelector));
        return driver.findElement(continueBtnSelector);
    }



}


