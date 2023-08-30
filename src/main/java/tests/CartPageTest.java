package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.MainPage;
import pageObjects.OverviewPage;

public class CartPageTest extends  BaseTest{

    /*
    User Story:

    As an online shopper,
    I want to proceed to the checkout page after adding products to my cart,
    So that I can provide my personal details and finalize my purchase.

    Acceptance Criteria:

    When I have items in my cart and click on the 'Checkout' button, I should be taken to the checkout page.
    On the checkout page, I should see input fields for 'First Name', 'Last Name', and 'Zip'.
    These fields should be empty, ready for me to input my details.

    Feature: Proceeding to Checkout

    Scenario: Verify that after adding products to the cart and clicking the 'Checkout' button, the checkout page is displayed with necessary input fields.
      Given the user has items in the cart
      And the user is on the cart page
      When the user clicks the 'Checkout' button
      Then the user is taken to the checkout page
      And the user sees empty input fields for 'First Name', 'Last Name', and 'Zip'
     */

    @Test
    public void cartPage_clickCheckout_checkotPageOpen (){
        MainPage mainPage = new MainPage(driver);
        //  Ensure the main page is open
        Assert.assertTrue(mainPage.isOpen());
        // Given the shopping cart item count indicator is not displayed
        Assert.assertTrue(mainPage.isShoppingCartItemCountDisplayed());
        // click Add to Cart
        mainPage.getBackpackAddToCartItem().click();
        // Assert item is added
        Assert.assertEquals("1", mainPage.getShoppingCartItemCount());
        // go to cart
        mainPage.goToCart();
        // Create CartPage
        CartPage cartPage = new CartPage(driver);
        // Assert cart page is open
        Assert.assertTrue(cartPage.isOpen());
        // Click checkout button
        cartPage.clickCheckoutButton();
        // initialize checkout page
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        // Assert checkout info page appear
        checkoutPage.isOpen();

        // Assert all fields are blank
        Assert.assertEquals(checkoutPage.getFirstNameFieldValue(), "", "First Name field is not blank");
        Assert.assertEquals(checkoutPage.getLastNameFieldValue(), "", "Last Name field is not blank");
        Assert.assertEquals(checkoutPage.getZipFieldValue(), "", "Zip field is not blank");
    }
    @Parameters({"firstName", "lastName", "zip"})
    @Test
    /*
    User Story:
    As an online shopper,
    I want to input my personal details during checkout,
    So that I can proceed to view the overview of my order before confirming the purchase.

    Acceptance Criteria:

    When I am on the checkout info page, I should see input fields for First Name, Last Name, and Zip.
    After entering information into the First Name, Last Name, and Zip fields, I should be able to click a button to proceed.
    Upon clicking the button to proceed, I should be redirected to the checkout overview page.

    Feature: Completing checkout details

    Scenario: Verify that after entering required personal details, the user is redirected to the checkout overview page.
        Given the user is on the checkout info page
        When the user enters valid information for First Name, Last Name, and Zip
        And the user clicks the button to proceed
        Then the user should be redirected to the checkout overview page
        And the user should be able to view details of their orde
     */
    public void checkoutInfo_enterDetails_redirectToOverview(String firstName, String lastName, String zip){
        //  Ensure the main page is open
        Assert.assertTrue(mainPage.isOpen());
        // Given the shopping cart item count indicator is not displayed
        Assert.assertTrue(mainPage.isShoppingCartItemCountDisplayed());
        // click Add to Cart
        mainPage.getBackpackAddToCartItem().click();
        // go to cart
        mainPage.goToCart();
        // Create CartPage
        CartPage cartPage = new CartPage(driver);
        // Assert cart page is open
        Assert.assertTrue(cartPage.isOpen());
        // Click checkout button
        cartPage.clickCheckoutButton();
        // initialize checkout page
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        // Assert checkout info page appear
        checkoutPage.isOpen();
        // input user info
        checkoutPage.getFirstNameField().sendKeys(firstName);
        checkoutPage.getLastNameField().sendKeys(lastName);
        checkoutPage.getZipField().sendKeys(zip);
        // click continue
        checkoutPage.getContinueButton().click();
        OverviewPage overviewPage = new OverviewPage(driver);
        Assert.assertTrue(overviewPage.isOpen());


    }

}
