import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task10 {

    public static void main(String[] args) {

        // Open Chrome
        WebDriver driver = new ChromeDriver();

        // Open jQuery UI
        driver.get("https://jqueryui.com/");

        // Maximize browser
        driver.manage().window().maximize();

        // Click Dialog from the left menu
        driver.findElement(By.linkText("Dialog")).click();

        // Switch to the demo iframe
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        // Click the close icon on the dialog
        driver.findElement(By.cssSelector(".ui-dialog-titlebar-close")).click();

        // Switch back to the main page
        driver.switchTo().defaultContent();

        // Close browser
        driver.quit();
    }
}