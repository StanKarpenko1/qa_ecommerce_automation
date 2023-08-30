package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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

    // click on cart
    public void goToCart (){
        getShoppingCartIcon().click();
    }

    // cart button
    public List <WebElement> getAddToCartButton () {
        By addToCartButton = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
        // since Add to Cart buttons turns to Remove, when no one left it reurns an empty list
        try{
            return driver.findElements(addToCartButton);
        } catch (NoSuchElementException e) {
            return new ArrayList<>(); // return an empty list if no elements found
        }
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
    public WebElement getBackPackRemoveButton (){
        By BackpackAddRemoveButton = By.id("remove-sauce-labs-backpack");
        wait.until(ExpectedConditions.visibilityOfElementLocated(BackpackAddRemoveButton));
        return driver.findElement(BackpackAddRemoveButton);
    }

    public String getShoppingCartItemCount(){
        By cartItemCountLocator =  By.cssSelector(".shopping_cart_badge");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemCountLocator));
        WebElement cartCountElement = driver.findElement(cartItemCountLocator);
        return cartCountElement.getText().trim();
    }

    public String getBackpackProductName () {
        By backpackNameLocator = By.id("item_4_title_link");
        wait.until(ExpectedConditions.visibilityOfElementLocated(backpackNameLocator));
        return driver.findElement(backpackNameLocator).getText().trim();
    }

    public String getBackpackProductPrice () {
        By backpackPriceLocator = By.cssSelector("#inventory_container > div > div:nth-child(1) > div.inventory_item_description > div.pricebar > div");
        wait.until(ExpectedConditions.visibilityOfElementLocated(backpackPriceLocator));
        return driver.findElement(backpackPriceLocator).getText().trim();
    }




}


