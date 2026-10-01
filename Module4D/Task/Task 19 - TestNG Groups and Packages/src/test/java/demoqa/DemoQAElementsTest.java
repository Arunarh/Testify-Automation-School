package demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoQAElementsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyElementsPage() {

        driver.get("https://demoqa.com/");

        driver.findElement(
                By.xpath("//div[contains(@class,'card-body')][.//h5[text()='Elements']]")
        ).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://demoqa.com/elements"
        );

        System.out.println(
                "DemoQA Elements page verified successfully."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}