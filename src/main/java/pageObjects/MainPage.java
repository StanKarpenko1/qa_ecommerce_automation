package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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
    // cart button
    private List <WebElement> getAddToCartButton () {
        By addToCartButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        return driver.findElements(addToCartButton);
    }
    public int getNumberAddToCartButtons (){
        return getAddToCartButton().size();
    }
    // item image
    private List <WebElement> getItemImg () {
        By inventoryItemImg = By.cssSelector(".inventory_item_img");
        wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemImg));
        return driver.findElements(inventoryItemImg);
    }
    public int getNumberItemImg (){
        return getItemImg().size();
    }
    // Item Name
    private List<WebElement> getItemNames() {
        By inventoryItemName = By.cssSelector(".inventory_item_name");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItemName));
        return driver.findElements(inventoryItemName);
    }
    public int getNumberItemNames (){
        return getItemNames().size();
    }

    // Item Desc
    private List<WebElement> getItemDescriptions() {
        By inventoryItemDesc = By.cssSelector(".inventory_item_desc");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItemDesc));
        return driver.findElements(inventoryItemDesc);
    }
    public int getNumberItemDesc () {
        return getItemDescriptions().size();
    }

    // Item Price
    private List<WebElement> getItemPrices() {
        By inventoryItemPrice = By.cssSelector(".inventory_item_price");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItemPrice));
        return driver.findElements(inventoryItemPrice);
    }
    public int getNumberItemPrices() {
        return getItemPrices().size();
    }
}
