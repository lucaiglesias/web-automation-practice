package tests.DesignPatterns.pages;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class TestPOM {
    protected WebDriver driver;

    @BeforeEach
    // Setting browser and webpage before each test
    public void setup(){
        //Options' object
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        // Disables save password and breach alerts from Chrome
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }


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

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pause of 3 secs and close browser
        Thread.sleep(3000);
        driver.quit();
    }
}
