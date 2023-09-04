package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.BaseTest;

public class CheckoutCompletePage extends BasePage {
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen(){
        try {
            return getCheckoutContainer().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public WebElement getCheckoutContainer() {
        By checkoutContainer = By.id("checkout_complete_container");
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutContainer));
        return driver.findElement(checkoutContainer);
    }

    public WebElement getSuccessMessage(){
        By successMessageLocator = By.xpath("//*[contains(text(), \"Your order has been dispatched\")]");
        wait.until((ExpectedConditions.visibilityOfElementLocated(successMessageLocator)));
        return driver.findElement(successMessageLocator);
    }
}
