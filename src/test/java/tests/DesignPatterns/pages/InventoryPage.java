package tests.DesignPatterns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    WebDriver driver;
    WebDriverWait wait; // Time checker

    //Locators
    //private By shoppingCart = By.className("shopping_cart_link");
    private By titleProducts = By.className("title");
    private By inventoryItem = By.className("inventory_item_name");
    private By addButton = By.className("btn_inventory");

    //Constructor
    public InventoryPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    //Boolean to check if Inventory Page is loaded
    public boolean isPageLoaded(){
        //
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleProducts));
        return title.getText().equals("Products");
    }

    public String addItemCart(int index){
        //Use 'wait' so that Selenium waits up to 10 seconds that the element appears on the page
        //When it appears it moves to the following step.
        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("inventory_item")));
        String name = "";
        if (items.size() > 1){
            name = items.get(index).findElement(inventoryItem).getText();
            items.get(index).findElement(addButton).click();
        }
        else{
            System.out.println("Not enough products on the list");
        }
        return name;
    }

}
