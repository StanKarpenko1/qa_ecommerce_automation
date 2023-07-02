package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class LoginPageTest extends BaseTest {

    @Test
    public void login_correctCredentials_loggedToApp() {
        System.out.println("Debug: username = " + username);
        System.out.println("Debug: password = " + password);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        MainPage mainPage = loginPage.loginToApp(username, password);
        Assert.assertTrue(mainPage.isOpen());
    }

}
