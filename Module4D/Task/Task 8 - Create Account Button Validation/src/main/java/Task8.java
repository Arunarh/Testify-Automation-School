import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task8 {

    public static void main(String[] args) {

        // Open Chrome
        WebDriver driver = new ChromeDriver();

        // Open the Sign Up page
        driver.get("https://idorenyinankoh.github.io/loginPage/");

        // Maximize browser
        driver.manage().window().maximize();

        // Locate Create Account button
        WebElement createAccountButton =
                driver.findElement(By.id("create"));

        // Check if Create Account is enabled BEFORE filling the form
        boolean beforeFilling = createAccountButton.isEnabled();

        System.out.println("Create Account enabled BEFORE filling: "
                + beforeFilling);

        // Fill First Name
        driver.findElement(By.id("firstName"))
                .sendKeys("Aruna");

        // Fill Last Name
        driver.findElement(By.id("lastName"))
                .sendKeys("Oluwasegun");

        // Fill Email
        driver.findElement(By.id("email"))
                .sendKeys("aruna.test@example.com");

        // Select Gender
        driver.findElement(By.id("male"))
                .click();

        // Fill Password
        driver.findElement(By.id("password"))
                .sendKeys("Test@12345");

        // Fill Confirm Password
        driver.findElement(By.id("confirmPass"))
                .sendKeys("Test@12345");

        // Fill Tell us about you
        driver.findElement(By.id("xpLevel"))
                .sendKeys("Beginner");

        // Check if Create Account is enabled AFTER filling the form
        boolean afterFilling = createAccountButton.isEnabled();

        System.out.println("Create Account enabled AFTER filling: "
                + afterFilling);

        // Close browser
        driver.quit();
    }
}