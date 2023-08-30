package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OverviewPage extends BasePage {
    public OverviewPage (WebDriver driver) {super(driver);}

    public boolean isOpen(){
        try {
            return getFinishBtnField().isDisplayed();
        } catch (TimeoutException eeee){
            return false;
        }
    }

    private WebElement getFinishBtnField() {
        By firstNameLocator = By.id("finish");
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameLocator));
        return driver.findElement(firstNameLocator);
    }
}
