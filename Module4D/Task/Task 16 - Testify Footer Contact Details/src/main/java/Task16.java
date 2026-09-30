import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task16 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @Test
    public void verifyTestifyFooterContactDetails() {

        driver.get("https://testifyltd.com/");

        // Scroll to the bottom of the homepage
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Email
        WebElement emailElement =
                driver.findElement(
                        By.cssSelector(
                                "a[href='mailto:business@testifyltd.com']"
                        )
                );

        String email = emailElement.getText();

        System.out.println("Email: " + email);

        Assert.assertEquals(
                email,
                "business@testifyltd.com"
        );


        // Phone
        WebElement phoneElement =
                driver.findElement(
                        By.cssSelector(
                                "a[href='tel:+2349091366160']"
                        )
                );

        String phone = phoneElement.getText();

        System.out.println("Phone: " + phone);

        Assert.assertEquals(
                phone,
                "(+234)909-136-6160"
        );


        // Location
        WebElement locationElement =
                driver.findElement(
                        By.xpath(
                                "//p[normalize-space()='From Nigeria to the world']"
                        )
                );

        String location = locationElement.getText();

        System.out.println("Location: " + location);

        Assert.assertTrue(
                location.contains("Nigeria")
        );


        System.out.println(
                "All Testify footer contact details verified successfully."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}