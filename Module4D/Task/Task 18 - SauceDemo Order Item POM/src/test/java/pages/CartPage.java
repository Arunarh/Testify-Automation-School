package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    WebDriver driver;

    By backpack = By.id("item_4_title_link");
    By bikeLight = By.id("item_0_title_link");
    By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBackpackName() {
        return driver.findElement(backpack).getText();
    }

    public String getBikeLightName() {
        return driver.findElement(bikeLight).getText();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}