package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DescriptionPage;

public class MainPageTest extends BaseTest{
    @Test
    public void mainPage_productListDisplayed() {
    /*
    User Story:
    As a user,
    I want to be sure that the main page opens correctly after logging in
    So that I can browse through the products and make a purchase.
    */

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

    @Test
    public void mainPage_productDescriptionIsOpen_clickImg () {
           /*
        User Story:
        As an online shopper,
        I want to click on a product image from the main page,
        So that I can view the details of the product.

        Acceptance Criteria:

        1. When I click on a product image, The new page I am redirected to should be a product detail page.
        2. I should see a single product photo on the new page.
        3. The product detail page should correspond to the product I clicked on.
        */

        Assert.assertTrue(mainPage.isOpen()); // Ensure the main page is open
        String mainPageProductDescription = mainPage.clickRandomImageAndGetDescription();
        DescriptionPage descriptionPage = new DescriptionPage(driver);
        Assert.assertTrue(descriptionPage.isOpen()); // assert desc page is open, Criteria #1
        Assert.assertEquals(1, descriptionPage.getNumberOfImg(), "The description page has more than 1 img's."); // Criteria #2
        // Assert description match on the main page and description page, Criteria #3
        String descriptionPageProductDesc = descriptionPage.getProductDescriptionText();
        Assert.assertEquals(descriptionPageProductDesc, mainPageProductDescription, "The product description on the main page does not match the one on the description page.");
    }
    @Test
    public void mainPage_productDescriptionIsOpen_clickDescription () {
          /*
        User Story:
        As an online shopper,
        I want to click on a product description from the main page,
        So that I can view the details of the product.

        Acceptance Criteria:

        1. When I click on a product description, The new page I am redirected to should be a product detail page.
        2. I should see a single product photo on the new page.
        3. The product detail page should correspond to the product I clicked on.
        */
        Assert.assertTrue(mainPage.isOpen()); // Ensure the main page is open
        String mainPageProductDescription = mainPage.clickRandomDescriptionAndGetNewDesc();
        DescriptionPage descriptionPage = new DescriptionPage(driver);
        Assert.assertTrue(descriptionPage.isOpen()); // assert desc page is open, Criteria #1
        Assert.assertEquals(1, descriptionPage.getNumberOfImg(), "The description page has more than 1 img's."); // Criteria #2
        String descriptionPageProductDesc = descriptionPage.getProductDescriptionText();
        Assert.assertEquals(descriptionPageProductDesc, mainPageProductDescription, "The product description on the main page does not match the one on the description page.");
    }
}
