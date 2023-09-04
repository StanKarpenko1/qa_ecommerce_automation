package tests;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.MainPage;

import java.time.Duration;
import java.util.List;


public class AddToCartTest extends BaseTest {

    @Test
    public void addToCart_addSingleProduct_productAdded () {
        //  Ensure the main page is open
        Assert.assertTrue(mainPage.isOpen());
        // Given the shopping cart item count indicator is not displayed
        Assert.assertTrue(mainPage.isShoppingCartItemCountDisplayed());
        // click Add to Cart
        mainPage.getBackpackAddToCartItem().click();
        // the shopping cart item count indicator becomes visible
        Assert.assertTrue(mainPage.shoppingCartCountItem().isDisplayed());
        // cart item count indicator displays '1'
        Assert.assertEquals("1", mainPage.getShoppingCartItemCount());
        // 'Add to Cart button changed to 'Remove'
        Assert.assertTrue(mainPage.getBackPackRemoveButton().isDisplayed());
        // Ensure the user is still on the main page
        Assert.assertTrue(mainPage.isOpen());
    }
    @Test
    public void removeFromCartSingle_fromMainPage_productREmoved (){
        //  Ensure the main page is open
        Assert.assertTrue(mainPage.isOpen());
        // Given the shopping cart item count indicator is not displayed
        Assert.assertTrue(mainPage.isShoppingCartItemCountDisplayed());
        // click Add to Cart
        mainPage.getBackpackAddToCartItem().click();
        // 'Add to Cart button changed to 'Remove'
        Assert.assertTrue(mainPage.getBackPackRemoveButton().isDisplayed());
        // click on Remove button
        mainPage.getBackPackRemoveButton().click();
        // ensure 'Add to Cart button is visible again
        Assert.assertTrue(mainPage.getBackpackAddToCartItem().isDisplayed());
    }

    @Test

    public void addToCart_addMultipleProduct_productAdded () {
        //  Ensure the main page is open
        Assert.assertTrue(mainPage.isOpen());
        int expectedItemCount = 0;

        while (true) {
            try {
                List<WebElement> buttons = mainPage.getAddToCartButton();

                if (buttons.size() == 0) {
                    break;
                }
                // Click "Add to Cart" if it exists
                WebElement firstButton = buttons.get(0);
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(firstButton));
                firstButton.click();
                // count increase by 1 after each click
                expectedItemCount++;
            } catch (TimeoutException e) {
                System.out.println("error: " + e);
                break; // exit loop if no more buttons found
            }
        }
            // current count tag appear on the cart
            String currentItemCount = mainPage.getShoppingCartItemCount();
            // Assertion
            Assert.assertEquals(String.valueOf(expectedItemCount), currentItemCount, "Cart item count mismatch after adding item. Expected items count: " + expectedItemCount + ", current item count: " + currentItemCount);
        }

        @Test
        public void addToCart_proceedToCart_infoMatch (){
            //  Ensure the main page is open
            Assert.assertTrue(mainPage.isOpen());
            // Given the shopping cart item count indicator is not displayed
            Assert.assertTrue(mainPage.isShoppingCartItemCountDisplayed());

            // Fetch the name and price from the product page
            String mainPageProdName = mainPage.getBackpackProductName().trim();
            String mainPageProdPrice = mainPage.getBackpackProductPrice().trim();

            // click Add to Cart
            mainPage.getBackpackAddToCartItem().click();
            // go to cart
            mainPage.goToCart();
            // Create CartPage
            CartPage cartPage = new CartPage(driver);
            // Assert cart page is open
            Assert.assertTrue(cartPage.isOpen());

            // Fetch the name and price from the cart page
            String cartPageProdName = cartPage.getCartProductName().trim();
            String cartPageProdPrice = cartPage.getCartProductPrice().trim();

            // Assert name and price
            Assert.assertEquals(mainPageProdName, cartPageProdName, "Product name does not match between main page: " +mainPageProdName+ " and cart: " + cartPageProdName);
            Assert.assertEquals(mainPageProdPrice, cartPageProdPrice, "Product price does not match between main page: " + mainPageProdPrice+  "and cart: " + cartPageProdPrice);
        }

    }



