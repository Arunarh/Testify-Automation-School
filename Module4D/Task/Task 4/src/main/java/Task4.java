import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task4 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("http://demo.guru99.com/");

        driver.manage().window().maximize();

        driver.findElement(By.linkText("Security Project")).click();

        driver.findElement(By.name("uid")).sendKeys("test@example.com");

        driver.findElement(By.name("password")).sendKeys("Test12345");
    }
}