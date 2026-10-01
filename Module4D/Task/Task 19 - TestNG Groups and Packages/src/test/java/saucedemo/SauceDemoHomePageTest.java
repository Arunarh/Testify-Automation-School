package saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoHomePageTest {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(groups = "homepage")
    public void verifySauceDemoHomePage() {
        driver.get("https://www.saucedemo.com/");

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/"
        );

        System.out.println(
                "SauceDemo homepage verified successfully."
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}