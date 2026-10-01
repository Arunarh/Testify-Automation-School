package demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DemoQAHomePageTest {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(groups = "homepage")
    public void verifyDemoQAHomePage() {
        driver.get("https://demoqa.com/");

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://demoqa.com/"
        );

        System.out.println(
                "DemoQA homepage verified successfully."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}