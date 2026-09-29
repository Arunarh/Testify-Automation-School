import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Task7 {

    public static void main(String[] args) {

        // Open Chrome
        WebDriver driver = new ChromeDriver();

        // Open World Weather Information Service
        driver.get("https://worldweather.wmo.int/en/home.html");

        // Maximize browser
        driver.manage().window().maximize();

        // Wait
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        // Find the search box
        WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("q_search")
                )
        );

        // Search for Lagos
        searchBox.sendKeys("Lagos");

        // Select Lagos from the suggestions
        searchBox.sendKeys(Keys.ARROW_DOWN);
        searchBox.sendKeys(Keys.ENTER);

        // Wait until the Lagos city page loads
        wait.until(
                ExpectedConditions.urlContains("city.html")
        );

        // Wait for the forecast days to appear
        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector(".city_forecast_day_object"),
                        0
                )
        );

        // Get all forecast days
        List<WebElement> forecastDays = driver.findElements(
                By.cssSelector(".city_forecast_day_object")
        );

        // Print the result
        System.out.println();
        System.out.println("===== LAGOS WEATHER FORECAST =====");

        for (WebElement day : forecastDays) {

            String date = day.findElement(
                    By.cssSelector(".city_weekday_n_date")
            ).getText();

            String description = day.findElement(
                    By.cssSelector(".city_fc_desc")
            ).getText();

            System.out.println(date + " - " + description);
        }

        System.out.println("=================================");

        // Close browser
        driver.quit();
    }
}