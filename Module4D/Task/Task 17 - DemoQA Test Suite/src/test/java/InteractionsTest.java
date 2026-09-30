import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InteractionsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyInteractionsPage() {

        driver.get("https://demoqa.com/");

        WebElement interactionsCard =
                driver.findElement(
                        By.xpath("//div[contains(@class,'card-body')][.//h5[text()='Interactions']]")
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        interactionsCard
                );

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        interactionsCard
                );

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://demoqa.com/interaction"
        );

        System.out.println(
                "Interactions page verified successfully."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}