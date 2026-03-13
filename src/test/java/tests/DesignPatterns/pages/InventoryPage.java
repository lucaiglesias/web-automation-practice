package tests.DesignPatterns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    //Locators
    //private By shoppingCart = By.className("shopping_cart_link");
    private By titleProducts = By.className("title");

    //Constructor
    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }


    //Boolean to check if Inventory Page is loaded
    public boolean isPageLoaded(){
        return driver.findElement(titleProducts).getText().equals("Products");
    }

}
