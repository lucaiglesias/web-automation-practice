package tests.DesignPatterns.pages;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.base.BaseTest;


public class TestPOM extends BaseTest {

    // Test using only POM
    @Test
    public void successLoginPOM() {
        LoginPage loginPage = new LoginPage(driver); // Instantiate LoginPage

        loginPage.writeUser("standard_user"); //calls method that reads the string user
        loginPage.writePassword("secret_sauce");//calls method that reads the string password
        loginPage.clickButton();//calls method that finds and click on button

        // Validate if the open page is the following page from the login
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory.html"));

    }

    //Test using POM with Fluent Interface
    @Test
    public void successLoginFluentInterface(){
        LoginPage loginPage = new LoginPage(driver);

        // Method clickButton() returns the new page.
        InventoryPage inventory = loginPage.writeUser("standard_user")
                .writePassword("secret_sauce")
                .clickButton();

        Assertions.assertTrue(inventory.isPageLoaded());
        String productName1 = inventory.addItemCart(0);
        Assertions.assertEquals("Sauce Labs Backpack", productName1);

    }

    //Same as previous test, but now with report by Allure
    @Test
    @Feature("Login")
    @Story("Successful Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if regular user can access Inventory webpage using Fluent Interface")
    public void successLoginFluentInterfaceWithAllure(){
        LoginPage loginPage = new LoginPage(driver);

        // Method clickButton() returns the new page.
        InventoryPage inventory = loginPage.writeUser("standard_user")
                .writePassword("secret_sauce")
                .clickButton();

        Assertions.assertTrue(inventory.isPageLoaded());

    }

    //Same as previous test, but now with Fail
    @Test
    @Feature("Login")
    @Story("Fail Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if regular user can access Inventory webpage using Fluent Interface")
    public void failLoginToGetScreenshot(){
        LoginPage loginPage = new LoginPage(driver);

        // Method clickButton() returns the new page.
        InventoryPage inventory = loginPage.writeUser("standard_user")
                .writePassword("secret_sauce")
                .clickButton();

        Assertions.assertTrue(inventory.isPageLoaded());

        String productName = inventory.addItemCart(0);
        //Error on purpose
        Assertions.assertEquals("Red T-Shirt", productName, "Product incorrect");

    }

    //Testing multiple happy paths logins
    @ParameterizedTest
    //Argument source that reads comma-separated values (CSV)
    @CsvSource({
            "standard_user, secret_sauce",
            "problem_user, secret_sauce",
            "performance_glitch_user, secret_sauce"
    })
    public void happyPathLogin(String user, String password){
        LoginPage loginPage = new LoginPage(driver);

        InventoryPage inventory = loginPage.writeUser(user)
                .writePassword(password)
                .clickButton();

        Assertions.assertTrue(inventory.isPageLoaded(), "Login failed for user: "+ user);

    }

    //Testing multiple sad paths logins
    @ParameterizedTest
    //Argument source that reads comma-separated values (CSV)
    //Define a delimiter to avoid conflict with comma on sentence.
    @CsvSource(delimiter = '|', value = {
            "locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.",
            "wrong_user | wrong_password | Epic sadface: Username and password do not match any user in this service"
    })
    public void sadPathLogin(String user, String password, String errorMessage){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.writeUser(user).writePassword(password).clickButton();

        Assertions.assertEquals(errorMessage, loginPage.getErrorMessage());

    }

}
