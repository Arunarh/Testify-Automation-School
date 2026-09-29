import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task9 {

    public static void main(String[] args) {

        // Open Chrome
        WebDriver driver = new ChromeDriver();

        // Open SauceDemo
        driver.get("https://www.saucedemo.com/");

        // Maximize browser
        driver.manage().window().maximize();

        // Login
        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        // Navigate back to the login screen
        driver.navigate().back();

        // Locate Login button
        WebElement loginButton =
                driver.findElement(By.id("login-button"));

        // Print Login button VALUE attribute
        String loginButtonValue =
                loginButton.getAttribute("value");

        System.out.println("Login button VALUE: "
                + loginButtonValue);

        // Navigate forward to the homepage
        driver.navigate().forward();

        // Locate a product name
        WebElement product =
                driver.findElement(By.className("inventory_item_name"));

        // Print product name
        System.out.println("Product name: "
                + product.getText());

        // Close browser
        driver.quit();
    }
}