package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;

public class LoginPageTest extends BaseTest {

    // Positive test scenarios
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

    // Negative test scenarios
    @Test
    public void login_incorrectCredentials_errorDisplayed (){
        /*
    User Story:
    As a user, I want to be shown an error message when I try to log in
    with incorrect credentials so that I can understand that the login was unsuccessful.
    */
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        loginPage.loginToApp("wrongUsername", "wrongPassword");
        Assert.assertTrue(loginPage.isError());
    }
    @Test
       /*
    User Story:
    As a user,
    I want to be shown an error message when I attempt to log in without entering any credentials,
    So that I can be reminded to provide necessary information for login.
    */
    public void login_emptyCredentials_errorDisplayed (){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        // Clear creddentials
        loginPage.clearCredentials();
        loginPage.loginToApp("", "");
        Assert.assertTrue(loginPage.isError() && loginPage.isUsernameRequiredError()) ;
    }
    @Test
       /*
    User Story:
    As a user, I want to be notified when I enter a valid username but leave the password field empty
    so that I can be reminded to enter my password before attempting to login.
    */

    public void login_validUsernameEmptyPass_errorDisplayed (){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(url);
        // Clear creddentials
        loginPage.clearCredentials();
        loginPage.loginToApp(username, "");
        Assert.assertTrue(loginPage.isError() && loginPage.isPassRequiredError()) ;
    }
}
