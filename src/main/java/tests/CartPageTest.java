package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class CartPageTest extends  BaseTest{
    private String firstName;
    private String lastName;
    private String zip;

    @Parameters({"firstName", "lastName", "zip"})
    @BeforeMethod
    public void setupCheckoutParameters (String firstName, String lastName, String zip){
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
    }

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
    @Test

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

    @Test
    public void addToCart_proceedToCheckout_checkoutComplete (){
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

        // click checkout
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
        // click Finish button
        overviewPage.getFinishBtnField().click();
        // checkout successful
        CheckoutCompletePage checkoutCompete = new CheckoutCompletePage(driver);
        checkoutPage.isOpen();
        Assert.assertTrue(checkoutCompete.getSuccessMessage().isDisplayed(), "The element is not visible.");
    }
}
