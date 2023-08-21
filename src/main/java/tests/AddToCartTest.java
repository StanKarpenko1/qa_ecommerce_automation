package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.MainPage;

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
        // Ensure the user is still on the main page
        Assert.assertTrue(mainPage.isOpen());
    }
}
