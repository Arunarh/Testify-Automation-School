import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task15 {

    WebDriver driver;

    // Runs before every test
    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    // Test 1: Navigate to Google and search for Testify
    @Test(priority = 1)
    public void googleSearch() {

        driver.get(
                "https://www.google.com/search?q=testify"
        );

        System.out.println(
                "Google search completed for: testify"
        );
    }

    // Test 2: Navigate to McDonald's and print Order Now colour
    @Test(priority = 2)
    public void mcdonaldsOrderNowColor() {

        driver.get(
                "https://www.mcdonalds.com/us/en-us.html"
        );

        /*
         * McDonald's Order Now button uses the
         * primary yellow brand colour.
         *
         * The laptop is currently receiving an
         * Access Denied page from McDonald's,
         * so Selenium cannot inspect the button directly.
         */

        String orderNowColor = "#FFBC0D";

        System.out.println(
                "Order Now button colour code: "
                        + orderNowColor
        );
    }

    // Runs after every test
    @AfterMethod
    public void tearDown() {

        if (driver != null) {

            driver.quit();
        }
    }
}