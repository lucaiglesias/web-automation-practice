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
    public void testLogin(){

        // 1. Encontrar o campo de usuário e digitar.
        // 1. Find the user's textbox using the id and type the login
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        // 2. Encontrar o campo de senha e digitar
        // 2. Find the password's textbox using the if and type the password.
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        // 3. Clicar no botão de Login
        // 3. Click at the login's button.
        driver.findElement(By.id("login")).findElement(By.tagName("button")).click();

        // 4. Validação do teste usando JUnit
        // 4. Check if test passed using JUnit
        String message = driver.findElement(By.id("flash")).getText();
        //Test Pass
        Assertions.assertTrue(message.contains("You logged into a secure area!"),"Test Failed");

    }

    // Close browser after each test
    @AfterEach
    public void tearDown() throws InterruptedException {
        // 5. Pausa de 5sec e fecha a janela
        // 5. Pause of 5secs and close browser
        Thread.sleep(5000);
        driver.quit();
    }


}