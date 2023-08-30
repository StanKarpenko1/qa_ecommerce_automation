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
     /*

User Story:

    As an online shopper,
    I want to add items to my shopping cart
    So that I can review them later and decide to purchase them.

Acceptance Criteria:

    1. When I click the 'Add to Cart' button for a product, a notification should appear indicating the product has been added to the cart.
    2. The shopping cart icon at the top of the page should show a count of the items added to the cart.
    3. The count should increase each time a new item is added to the cart.

    */
    @Test
    /*
   Feature: Adding product to cart

  Scenario: Verify that clicking the 'Add to Cart' button displays the shopping cart item count indicator.
    Given the user is on the main page
    And the shopping cart item count indicator is not displayed
    When the user clicks the 'Add to Cart' button for a product
    Then the shopping cart item count indicator becomes visible
    And the shopping cart item count indicator displays '1'
    And the user is still on the main page
    And the 'Add to Cart' button of the corresponding item should change to 'Remove'
    */
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
    /*
    User Story: Removing Item from Cart from the Main Page

        As a customer browsing the e-commerce website,
        I want to have the ability to remove an item directly from the main product page after adding it to my cart,
        So that I can easily change my mind without having to navigate to the cart page.

    Acceptance Criteria:
        1. When I add an item to my cart from the main product page, the 'Add to Cart' button for that item changes to a 'Remove' button.
        2. When I click on the 'Remove' button, the item is immediately removed from my cart.
        3. Upon removal, the button text changes back to 'Add to Cart', indicating that the item can be added to the cart again.
        4. The cart icon's item count indicator reduces by one when an item is removed.
     */
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
    /*
    User story:
         As a user,
         I want the cart's item count indicator to show the exact number of products I've added,
         So that I can easily know how many items I have in my cart at any time.

    Acceptance Criteria:
        Feature: Reflecting accurate item count in the shopping cart indicator

     Scenario: Incremental addition of products to the cart and verifying the count after each addition
          Given the main page is open
          When I add a product to the cart
          Then the cart item count indicator should display the updated count
          And I should remain on the main page.
          And I repeat the above steps until "6" products are added
    */

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

        /*
        User Story:
        As an online shopper,
        I want to confirm my cart displays the correct product details
        So that I am confident in the item I am purchasing.

        1. After adding a single product to my cart and proceeding to the cart page,
        the product's name and price in the cart should match the details from the main page.

        Scenario: Ensure that a single product's details in the shopping cart match with its details from the main page.
        Given the user is on the main page
        And the shopping cart is empty
        When the user adds a single product to the cart and notes its details
        And then proceeds to the shopping cart page
        Then the product's name and price in the cart match the noted details from the main page.
        */

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



