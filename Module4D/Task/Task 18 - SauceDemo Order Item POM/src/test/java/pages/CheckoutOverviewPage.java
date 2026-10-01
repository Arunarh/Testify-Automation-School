package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage {

    WebDriver driver;

    By backpack = By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']");
    By bikeLight = By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Bike Light']");
    By finishButton = By.id("finish");
    By successMessage = By.className("complete-header");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBackpackName() {
        return driver.findElement(backpack).getText();
    }

    public String getBikeLightName() {
        return driver.findElement(bikeLight).getText();
    }

    public void clickFinish() {
        driver.findElement(finishButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}