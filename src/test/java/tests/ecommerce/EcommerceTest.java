package tests.ecommerce;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcommerceTest {
    WebDriver driver;

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

    @Test
    public void addProductToCart() throws InterruptedException {
        // 1. Login
        login("standard_user", "secret_sauce");

        // 2. Add first product on the list (id 0 / id 1) to cart and save name of product to check later
        String productName1 = selectProduct(0);
        String productName2 = selectProduct(1);

        // 3. Open cart
        openCart();

        // 4. Validate if the product in the cart is the same that was selected
        checkCart(productName1);
        checkCart(productName2);

        // 5. Check out
        checkOut();

        // 6. Enter user's info
        fillCustomerInfo("Luca", "Iglesias", "1G3I9O");

        // 7. Confirm if the order was made
        finishSale();


    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pause of 3 secs and close browser
        Thread.sleep(3000);
        driver.quit();
    }

    //Method to login on the store
    public void login(String user, String password){
        //Enter User
        driver.findElement(By.id("user-name")).sendKeys(user);
        //Enter Password
        driver.findElement(By.id("password")).sendKeys(password);
        //Click Login Button
        driver.findElement(By.id("login-button")).click();
    }

    //Method to open cart
    public void openCart(){
        driver.findElement(By.className("shopping_cart_link")).click();
    }


    //Method that selects the item you want to add to the cart, and return its name.
    public String selectProduct(int index){
        List<WebElement> items = driver.findElements(By.className("inventory_item"));
        String name = "";
        if (items.size() > 1){
            name = items.get(index).findElement(By.className("inventory_item_name")).getText();
            items.get(index).findElement(By.className("btn_inventory")).click();
        }
        else{
            System.out.println("Not enough products on the list");
        }
        return name;
    }

    //Method that check if the item added is on the cart
    public void checkCart(String name){
        List<WebElement> itemsCart = driver.findElements(By.className("inventory_item_name"));
        if (!itemsCart.isEmpty()){
            boolean found = false;
            for (WebElement itemCart : itemsCart){
                if(itemCart.getText().contains(name)){
                    found = true;
                    break;
                }
            }
            Assertions.assertTrue(found,"Test Failed, product '"+name+"' not found");
        }
        else{
            System.out.println("Cart is empty");
        }
    }

    //Method to check out
    public void checkOut() throws InterruptedException {
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);
    }

    //Method to enter the customer's info
    public void fillCustomerInfo(String fName, String lName, String zip) {

        driver.findElement(By.id("first-name")).sendKeys(fName);
        driver.findElement(By.id("last-name")).sendKeys(lName);
        driver.findElement(By.id("postal-code")).sendKeys(zip);
        driver.findElement(By.id("continue")).click();
    }

    //Method to finish sale
    public void finishSale(){
        driver.findElement(By.id("finish")).click();
        Assertions.assertTrue(driver.findElement(By.id("checkout_complete_container")).getText().contains("Thank you for your order!"), "Test failed!");
    }
}
