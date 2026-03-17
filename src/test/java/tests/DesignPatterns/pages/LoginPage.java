package tests.DesignPatterns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait; //Time checker

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.className("error-message-container");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        //Use 'wait' so that Selenium waits up to 10 seconds that the element appears on the page
        //When it appears it moves to the following step.
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        button.click();
        return new InventoryPage(driver);
    }

    public String getErrorMessage(){
        WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return Message.getText();


    }


}
