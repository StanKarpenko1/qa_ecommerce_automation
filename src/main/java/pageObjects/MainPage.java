package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() { // check if Main Page is open
        try {
            return  getShoppingCartIcon().isDisplayed();
        } catch (TimeoutException ee){
            return false;
        }
    }

    private WebElement getShoppingCartIcon() {
        By cartLocator = By.id("shopping_cart_container");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartLocator));
        return driver.findElement(cartLocator);
    }

}
