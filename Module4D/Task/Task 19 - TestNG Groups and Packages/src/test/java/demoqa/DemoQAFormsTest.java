package demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoQAFormsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyFormsPage() {

        driver.get("https://demoqa.com/");

        driver.findElement(
                By.xpath("//div[contains(@class,'card-body')][.//h5[text()='Forms']]")
        ).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://demoqa.com/forms"
        );

        System.out.println(
                "DemoQA Forms page verified successfully."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}