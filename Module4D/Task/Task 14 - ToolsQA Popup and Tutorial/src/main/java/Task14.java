import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task14 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            // Open ToolsQA
            driver.get("https://www.toolsqa.com/");

            // Maximize browser
            driver.manage().window().maximize();

            // Create explicit wait
            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(10)
                    );

            // Close cookie popup
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("accept-cookie-policy")
                    )
            ).click();

            System.out.println(
                    "Cookie popup closed successfully."
            );

            // Click Tutorials button
            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector(
                                    "a.navbar__tutorial-menu"
                            )
                    )
            ).click();

            System.out.println(
                    "Tutorials button clicked successfully."
            );

        } finally {

            driver.quit();
        }
    }
}