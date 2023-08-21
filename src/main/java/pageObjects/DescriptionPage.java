package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DescriptionPage extends BasePage {
    public By singleImgViewSelector = By.cssSelector(".inventory_details_img_container");

    public DescriptionPage (WebDriver driver){
        super(driver);
    }

    public boolean isOpen (){
        try {
            WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(singleImgViewSelector));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public int getNumberOfImg (){
        return driver.findElements(singleImgViewSelector).size();
    }

    public String getProductDescriptionText () {
        By descLocator = By.cssSelector("#inventory_item_container > div > div > div.inventory_details_desc_container > div.inventory_details_name.large_size");
        wait.until(ExpectedConditions.visibilityOfElementLocated(descLocator));
        return driver.findElement(descLocator).getText();
        }

    }
