package tests.DesignPatterns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /*
    //Simple methods for POM
    //Method to write User
    public void writeUser(String user) {
        driver.findElement(usernameField).sendKeys(user);
    }

    //Method to write password
    public void writePassword(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
    }

    //Method to click button
    public void clickButton() {
        driver.findElement(loginButton).click();
    }

    public void clickcart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }*/



    //Methods with Fluent Interface

    public LoginPage writeUser(String user) {
        driver.findElement(usernameField).sendKeys(user);
        return this;
    }

    public LoginPage writePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }


    //Method to click button and return new page
    public InventoryPage clickButton() {
        driver.findElement(loginButton).click();
        return new InventoryPage(driver);
    }


}
