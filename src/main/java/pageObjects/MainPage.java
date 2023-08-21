package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

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
        By inventoryItemImg = By.cssSelector(".inventory_item_img > a");
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

    // Click Product Image
    public String clickRandomImageAndGetDescription () {
        // generating random number to check the random product
        int i = new Random().nextInt(6); // generate random number 0 to 5
        String descSelector = String.format("#item_%d_title_link > div", i); // generating random string for desc selector
        String imgSelector = String.format("#item_%d_img_link > img", i); // generating random string for img selector
        WebElement productDescription = driver.findElement(By.cssSelector(descSelector)); // getting selector of randomly picked desc to assert it later
        String productDescriptionText = productDescription.getText();
        WebElement productImg = driver.findElement(By.cssSelector(imgSelector)); // getting selector for img to click on it
        productImg.click();

        return productDescriptionText;
    }
    // Click product description
    public  String clickRandomDescriptionAndGetNewDesc () {
        int i = new Random().nextInt(6); // generate random number 0 to 5
        String descSelector = String.format("#item_%d_title_link > div", i); // generating random string for desc selector
        WebElement productDescription = driver.findElement(By.cssSelector(descSelector)); // getting selector of randomly picked desc to assert it later
        String productDescriptionText = productDescription.getText();
        productDescription.click();

        return productDescriptionText;
    }
    public WebElement getAddToCartIdButton() {
        By addToCartLocator = By.id("add-to-cart-sauce-labs-backpack");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartLocator));
        return driver.findElement(addToCartLocator);
    }
    public boolean isShoppingCartItemCountDisplayed(){
        By shoppingCartCountItem = By.className("shopping_cart_badge");
        List <WebElement> elements = driver.findElements(shoppingCartCountItem);
        return elements.isEmpty();

    }
    public WebElement shoppingCartCountItem(){
        By shoppingCartCountItem = By.className("shopping_cart_badge");
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartCountItem));
        return driver.findElement(shoppingCartCountItem);
    }
    public WebElement getBackpackAddToCartItem () {
        By BackpackAddToCartItem = By.id("add-to-cart-sauce-labs-backpack");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BackpackAddToCartItem));
        return  driver.findElement(BackpackAddToCartItem);
    }

    public String getShoppingCartItemCount() {
        return shoppingCartCountItem().getText();
    }
}
