package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String usernameText) {
        driver.findElement(username).sendKeys(usernameText);
    }

    public void enterPassword(String passwordText) {
        driver.findElement(password).sendKeys(passwordText);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String usernameText, String passwordText) {
        enterUsername(usernameText);
        enterPassword(passwordText);
        clickLogin();
    }
}