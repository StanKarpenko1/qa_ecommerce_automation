package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen(){ // check if cart page is open
        try {
            return getCheckoutButton().isDisplayed();
        } catch (TimeoutException eee) {
            return false;
        }
    }

    private WebElement getCheckoutButton() {
        By checkoutButtonLocator = By.id("checkout");
        wait.until(ExpectedConditions.visibilityOfElementLocated((checkoutButtonLocator)));
        return driver.findElement(checkoutButtonLocator);
    }

    // Backpack product name on cart page
    public String getCartProductName() {
        By productNameLocator = By.id("item_4_title_link");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));
        return driver.findElement(productNameLocator).getText().trim();
    }
    // Backpack price on cart page
    public String getCartProductPrice() {
        By productPriceLocator = By.cssSelector("#cart_contents_container > div > div.cart_list > div.cart_item > div.cart_item_label > div.item_pricebar > div");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator));
        return driver.findElement(productPriceLocator).getText().trim();
    }
    public void clickCheckoutButton(){
        getCheckoutButton().click();
    }

}
