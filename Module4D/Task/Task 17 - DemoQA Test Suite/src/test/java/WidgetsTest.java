import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WidgetsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyWidgetsPage() {

        driver.get("https://demoqa.com/");

        WebElement widgetsCard =
                driver.findElement(
                        By.xpath("//div[contains(@class,'card-body')][.//h5[text()='Widgets']]")
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        widgetsCard
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        widgetsCard
                );

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://demoqa.com/widgets"
        );

        System.out.println(
                "Widgets page verified successfully."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}