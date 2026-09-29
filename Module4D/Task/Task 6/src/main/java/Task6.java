import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task6 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        // Navigate to SauceDemo
        driver.get("https://www.saucedemo.com/");

        // Login
        driver.findElement(By.cssSelector("#user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.cssSelector("#password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.cssSelector("#login-button"))
                .click();

        // Add only one product to cart
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"))
                .click();

        // Click cart icon
        driver.findElement(By.cssSelector(".shopping_cart_link"))
                .click();

        // Click Checkout
        driver.findElement(By.cssSelector("#checkout"))
                .click();
    }
}