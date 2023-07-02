package tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class LoginPageTest extends BaseTest {

    @Test
    public void login_correctCredentials_loggedToApp() {
        /*
    User Story:
    As a user, I want to be able to log in with correct credentials
    so that I can access the main page of the application.
    */

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        MainPage mainPage = loginPage.loginToApp(username, password);
        Assert.assertTrue(mainPage.isOpen());
    }

    @Test
    public void login_incorrectCredentials_errorDisplayed (){
        /*
    User Story:
    As a user, I want to be shown an error message when I try to log in
    with incorrect credentials so that I can understand that the login was unsuccessful.
    */
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        MainPage mainPage = loginPage.loginToApp("wrongUsername", "wrongPassword");
        Assert.assertTrue(loginPage.isError());
    }

}
