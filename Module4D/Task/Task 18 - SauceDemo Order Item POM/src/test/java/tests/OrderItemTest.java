package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class OrderItemTest {

    WebDriver driver;

    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    @Test
    public void verifyOrderItems() {

        // 1. Navigate to SauceDemo
        driver.get("https://www.saucedemo.com/");

        // 2. Login
        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        // 3. Add two items to cart
        productsPage.addBackpack();
        productsPage.addBikeLight();

        // 4. Open cart
        productsPage.clickCart();

        // 5. Verify items on YOUR CART page
        Assert.assertEquals(
                cartPage.getBackpackName(),
                "Sauce Labs Backpack"
        );

        Assert.assertEquals(
                cartPage.getBikeLightName(),
                "Sauce Labs Bike Light"
        );

        System.out.println(
                "Cart items verified successfully."
        );

        // 6. Click Checkout
        cartPage.clickCheckout();

        // 7. Enter checkout information
        checkoutPage.completeCheckoutInformation(
                "Aruna",
                "Oluwasegun",
                "100001"
        );

        // 8. Verify items on CHECKOUT: OVERVIEW page
        Assert.assertEquals(
                checkoutOverviewPage.getBackpackName(),
                "Sauce Labs Backpack"
        );

        Assert.assertEquals(
                checkoutOverviewPage.getBikeLightName(),
                "Sauce Labs Bike Light"
        );

        System.out.println(
                "Checkout overview items verified successfully."
        );

        // 9. Click Finish
        checkoutOverviewPage.clickFinish();

        // 10. Verify success message
        Assert.assertEquals(
                checkoutOverviewPage.getSuccessMessage(),
                "Thank you for your order!"
        );

        System.out.println(
                "Order completed successfully."
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}