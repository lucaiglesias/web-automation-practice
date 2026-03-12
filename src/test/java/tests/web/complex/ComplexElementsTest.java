package tests.web.complex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexElementsTest {

    WebDriver driver;

    @BeforeEach
    // Setting browser before each test
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
        driver.manage().window().maximize();
    }

    @Test
    public void selectElement(){
        //Open specific webpage
        driver.get("https://the-internet.herokuapp.com/dropdown");

        //Select item on dropdown menu
        selectFromDropdown("Option 2");

        //Validation
        String selectedText = getSelectedOptionText();
        Assertions.assertEquals("Option 2", selectedText, "Incorrect Selection");

    }

    @Test
    public void handleAlertTest(){
        //Open specific webpage
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        //Find button using xpath and clicking on it
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        //Clicking on ok to close pop-up
        driver.switchTo().alert().accept();

        //Validation
        Assertions.assertEquals("You clicked: Ok", driver.findElement(By.id("result")).getText(),"Incorrect button");

        //Find button using xpath and clicking on it
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        //Enter text and clicking on ok to close pop-up
        driver.switchTo().alert().sendKeys("nice to meet you");
        driver.switchTo().alert().accept();

        //Validation
        Assertions.assertEquals("You entered: nice to meet you", driver.findElement(By.id("result")).getText(),"Incorrect button");

    }

    @Test
    public void iFrameTest(){
        //Open specific webpage
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        //Switch to frame top
        driver.switchTo().frame("frame-top");

        //Switch to frame middle
        driver.switchTo().frame("frame-middle");

        //Validate text
        Assertions.assertEquals("MIDDLE", driver.findElement(By.id("content")).getText(),"Incorrect Frame");

        //Quit Frame
        driver.switchTo().defaultContent();

        //Swith to frame bottom
        driver.switchTo().frame("frame-bottom");

        //Validate text
        Assertions.assertEquals("BOTTOM", driver.findElement(By.xpath("//body")).getText(), "Incorrect Frame");


    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        // Pause of 3 secs and close browser
        Thread.sleep(3000);
        driver.quit();
    }

    //Multiple actions (Selection and validation) in only one method
   /* public void selectFromDropdown(String option){
        //Create Class
        WebElement selectElement = driver.findElement(By.id("dropdown"));
        Select select = new Select(selectElement);

        //Select the option by its index
        select.selectByIndex(2);

        //Confirm if the option selected is what we are looking for
        List<WebElement> optionList = select.getAllSelectedOptions();

        for(WebElement value : optionList){
            Assertions.assertTrue(value.getText().contains(option),"Incorrect selection");
            System.out.println(value.getText());
        }
    }*/

    //One action on method
    public void selectFromDropdown(String option){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByVisibleText(option);
    }
    //Validation
    public String getSelectedOptionText(){
        Select select = new Select(driver.findElement(By.id("dropdown")));
        return select.getFirstSelectedOption().getText();
    }
}
