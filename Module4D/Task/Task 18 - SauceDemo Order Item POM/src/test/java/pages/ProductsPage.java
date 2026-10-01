package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    WebDriver driver;

    By backpack = By.id("add-to-cart-sauce-labs-backpack");
    By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBackpack() {
        driver.findElement(backpack).click();
    }

    public void addBikeLight() {
        driver.findElement(bikeLight).click();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }
}