package tests.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class FirstTest {

    // Declaring the driver so that all methods can use it
    WebDriver driver;

    // Setting up the browser and open the page before each test
    @BeforeEach
    public void setup(){
        //Instantiate Chrome Driver
        driver = new ChromeDriver();
        //Open browser on the following page
        driver.get("https://the-internet.herokuapp.com/login");
        //Maximize window
        driver.manage().window().maximize();
    }

    // Test
    @Test
    public void testValidLogin(){

        // 1. Encontrar o campo de usuário e digitar.
        // 1. Find the user's textbox using the id and type the login
        enterUser("tomsmith");

        // 2. Encontrar o campo de senha e digitar
        // 2. Find the password's textbox using the if and type the password.
        enterPassword("SuperSecretPassword!");

        // 3. Clicar no botão de Login
        // 3. Click at the login's button.
        clickLoginButton();

        // 4. Validação do teste usando JUnit
        // 4. Using JUnit to check test
        validateMessage("You logged into a secure area!");

    }

    @Test
    public void testInvalidLogin(){

        // 1. Call method "enterUser" and type the incorrect username
        enterUser("incorrectUser");

        // 2. Click at the login's button
        clickLoginButton();

        // 3. Using JUnit to check test
        validateMessage("Your username is invalid!");
    }

    // Close browser after each test
    @AfterEach
    public void tearDown() throws InterruptedException {
        // 5. Pausa de 3 segs e fecha a janela
        // 5. Pause of 3 secs and close browser
        Thread.sleep(3000);
        driver.quit();
    }

    //Method to find the User's textbox and enter text
    public void enterUser(String user){
        driver.findElement(By.id("username")).sendKeys(user);
    }

    //Method to find the Password's textbox and enter text
    public void enterPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }

    //Method to find the Login's button and click it
    public void clickLoginButton(){
        driver.findElement(By.id("login")).findElement(By.tagName("button")).click();
    }

    //Method to validate message
    public void validateMessage(String header){
        String message = driver.findElement(By.id("flash")).getText();
        Assertions.assertTrue(message.contains(header), "Test Failed");
    }

}