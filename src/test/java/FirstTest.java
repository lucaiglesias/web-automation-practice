import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    @Test
    public void openBrowser() {
        // 1. Instancia o driver do Chrome (O Selenium 4 baixa o driver sozinho!)
        // 1. Instaciate Chrome's driver (Selenium download it by it self!)
        WebDriver driver = new ChromeDriver();

        // 2. Manda o navegador ir para o site
        // 2. Tell the browser to open the webpage
        driver.get("https://the-internet.herokuapp.com/login");

        // 3. Maximiza a janela para a gente enxergar melhor
        // 3. Make it full screen
        driver.manage().window().maximize();

        // 4. (Opcional) Fecha o navegador depois de 3 segundos para teste
        // 4. (Optional) Close it after 3 seconds
        driver.quit();
    }
}