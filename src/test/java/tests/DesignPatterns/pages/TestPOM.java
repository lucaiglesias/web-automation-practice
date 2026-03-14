package tests.DesignPatterns.pages;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    /*
    // Test using only POM
    @Test
    public void successLoginPOM() {
        LoginPage loginPage = new LoginPage(driver); // Instantiate LoginPage

        loginPage.writeUser("standard_user"); //calls method that reads the string user
        loginPage.writePassword("secret_sauce");//calls method that reads the string password
        loginPage.clickButton();//calls method that finds and click on button

        // Validate if the open page is the following page from the login
        Assertions.assertTrue(driver.getCurrentUrl().contains("inventory.html"));

    }*/

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

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pause of 3 secs and close browser
        Thread.sleep(3000);
        driver.quit();
    }
}
