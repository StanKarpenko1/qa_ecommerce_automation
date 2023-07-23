package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class MainPageTest extends BaseTest{
    @Test
    public void mainPage_productListDisplayed() {
    /*
    User Story:
    As a user,
    I want to be sure that the main page opens correctly after logging in
    So that I can browse through the products and make a purchase.
    */

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        MainPage mainPage = loginPage.loginToApp(username, password);
        Assert.assertTrue(mainPage.isOpen());

        int numOfAddToCartButtons = mainPage.getNumberAddToCartButtons();
        int numOfItemImgs = mainPage.getNumberItemImg();
        int numOfItemNames = mainPage.getNumberItemNames();
        int numOfItemDesc = mainPage.getNumberItemDesc();
        int numOfItemPrices = mainPage.getNumberItemPrices();

        Assert.assertTrue(numOfAddToCartButtons > 0 && numOfAddToCartButtons <= 6, "Number of Add to Cart buttons " + numOfAddToCartButtons + " is incorrect");
        Assert.assertTrue(numOfItemImgs > 0 && numOfItemImgs <= 6, "Number of product images " + numOfItemImgs + " is incorrect");
        Assert.assertTrue(numOfItemNames > 0 && numOfItemNames <= 6, "Number of product names " + numOfItemNames + " is incorrect");
        Assert.assertTrue(numOfItemDesc > 0 && numOfItemDesc <= 6, "Number of product descriptions " + numOfItemDesc + " is incorrect");
        Assert.assertTrue(numOfItemPrices > 0 && numOfItemPrices <= 6, "Number of product prices " + numOfItemPrices + " is incorrect");

        Assert.assertTrue(
                numOfAddToCartButtons == numOfItemImgs && numOfItemImgs == numOfItemNames && numOfItemNames == numOfItemDesc && numOfItemDesc == numOfItemPrices,
                "Mismatch in the number of elements for product components"
        );
    }

}
