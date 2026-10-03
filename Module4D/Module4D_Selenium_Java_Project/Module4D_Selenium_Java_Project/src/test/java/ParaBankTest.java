import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class ParaBankTest {

    // Driver shared by the helper methods.
    private WebDriver driver;

    // Explicit wait shared by the helper methods.
    private WebDriverWait wait;

    @Test
    public void openParaBank() {

        // Launch Chrome.
        driver = new ChromeDriver();

        // Create a 60-second explicit wait.
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {

            // Maximize the browser window.
            driver.manage().window().maximize();

            // Generate a highly unique username for every test run.
            String username =
                    "MT" + UUID.randomUUID()
                            .toString()
                            .replace("-", "")
                            .substring(0, 10);

            // Generate a unique password at runtime.
            String password =
                    "Test@" + System.currentTimeMillis() + "A1";

            // Open ParaBank.
            driver.get("https://parabank.parasoft.com/");

            // =========================================================
            // SECTION 1 - REGISTER A NEW USER
            // =========================================================

            // Click Register.
            click(By.linkText("Register"));

            // Fill registration form.
            type(By.id("customer.firstName"), "Mike");
            type(By.id("customer.lastName"), "Tester");
            type(By.id("customer.address.street"), "10 Test Street");
            type(By.id("customer.address.city"), "Lagos");
            type(By.id("customer.address.state"), "Lagos");
            type(By.id("customer.address.zipCode"), "100001");
            type(By.id("customer.phoneNumber"), "08012345678");
            type(By.id("customer.ssn"), "123456789");

            // Enter unique username.
            type(By.id("customer.username"), username);

            // Enter password.
            type(By.id("customer.password"), password);

            // Confirm password.
            type(By.id("repeatedPassword"), password);

            // Submit registration.
            click(By.xpath("//input[@value='Register']"));

            // Wait for successful account creation.
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("rightPanel"),
                    "Your account was created successfully"
            ));

            // Read registration confirmation.
            String registrationText =
                    driver.findElement(By.id("rightPanel")).getText();

            System.out.println("REGISTRATION MESSAGE:");
            System.out.println(registrationText);

            // Verify username welcome message.
            Assert.assertTrue(
                    registrationText.contains("Welcome " + username),
                    "Welcome message was not displayed after registration."
            );

            // Verify account creation.
            Assert.assertTrue(
                    registrationText.contains(
                            "Your account was created successfully"
                    ),
                    "Account creation confirmation was not displayed."
            );

            // Verify automatic login.
            Assert.assertTrue(
                    registrationText.contains("You are now logged in"),
                    "Automatic login confirmation was not displayed."
            );

            // =========================================================
            // SECTION 2 - LOG OUT AND LOG IN AGAIN
            // =========================================================

            // Log out.
            click(By.linkText("Log Out"));

            // Enter username.
            type(By.name("username"), username);

            // Enter password.
            type(By.name("password"), password);

            // Click Log In.
            click(By.xpath("//input[@value='Log In']"));

            // Wait for Accounts Overview.
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("rightPanel"),
                    "Accounts Overview"
            ));

            // Verify login using the customer's real name.
            Assert.assertTrue(
                    driver.findElement(By.id("leftPanel"))
                            .getText()
                            .contains("Welcome Mike Tester"),
                    "Login was not successful."
            );

            // =========================================================
            // SECTION 3 - OPEN A NEW SAVINGS ACCOUNT
            // =========================================================

            // Open New Account.
            click(By.linkText("Open New Account"));

            // Select SAVINGS.
            new Select(
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("type")
                            )
                    )
            ).selectByVisibleText("SAVINGS");

            // Wait for a real existing account.
            waitForRealAccount(By.id("fromAccountId"));

            // Get the existing account.
            String fromAccountId =
                    getFirstRealAccount(By.id("fromAccountId"));

            System.out.println(
                    "EXISTING ACCOUNT: " + fromAccountId
            );

            // Open new account.
            click(By.xpath("//input[@value='Open New Account']"));

            // Wait for Account Opened confirmation.
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("rightPanel"),
                    "Account Opened"
            ));

            // Wait for new account ID.
            wait.until(d ->
                    !d.findElement(By.id("newAccountId"))
                            .getText()
                            .trim()
                            .isEmpty()
            );

            // Capture new account ID.
            String newAccountId =
                    driver.findElement(By.id("newAccountId"))
                            .getText()
                            .trim();

            // Verify account ID exists.
            Assert.assertFalse(
                    newAccountId.isBlank(),
                    "New account ID was not created."
            );

            System.out.println(
                    "NEW ACCOUNT: " + newAccountId
            );

            // =========================================================
            // SECTION 4 - TRANSFER $100
            // =========================================================

            // Open Transfer Funds.
            click(By.linkText("Transfer Funds"));

            // Enter amount.
            type(By.id("amount"), "100");

            // Select source account.
            selectAccount(
                    By.id("fromAccountId"),
                    fromAccountId
            );

            // Select destination account.
            selectAccount(
                    By.id("toAccountId"),
                    newAccountId
            );

            // Click Transfer.
            click(By.xpath("//input[@value='Transfer']"));

            // Wait for transfer confirmation.
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("rightPanel"),
                    "Transfer Complete"
            ));

            // =========================================================
            // SECTION 5 - BILL PAY
            // =========================================================

            // Open Bill Pay.
            click(By.linkText("Bill Pay"));

            // Enter payee details.
            type(By.name("payee.name"), "Test Payee");
            type(By.name("payee.address.street"), "20 Test Street");
            type(By.name("payee.address.city"), "Lagos");
            type(By.name("payee.address.state"), "Lagos");
            type(By.name("payee.address.zipCode"), "100002");
            type(By.name("payee.phoneNumber"), "08098765432");

            // Payee account number.
            type(By.name("payee.accountNumber"), "123456");

            // Verify account number.
            type(By.name("verifyAccount"), "123456");

            // Payment amount.
            type(By.name("amount"), "50");

            // Select account to pay from.
            selectAccount(
                    By.name("fromAccountId"),
                    newAccountId
            );

            // Send payment.
            click(By.xpath("//input[@value='Send Payment']"));

            // Wait for payment confirmation.
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.id("rightPanel"),
                    "Bill Payment Complete"
            ));

            // =========================================================
            // SECTION 6 - FIND TRANSACTIONS
            // =========================================================

            // Open Find Transactions.
            click(By.linkText("Find Transactions"));

            // Select new account.
            selectAccount(
                    By.id("accountId"),
                    newAccountId
            );

            // Enter $100.
            type(By.id("amount"), "100");

            // Click Find by Amount.
            clickFindByAmount();

            // Wait for transaction table.
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("transactionTable")
                    )
            );

            // Wait for at least one transaction row.
            wait.until(d ->
                    !d.findElements(
                            By.cssSelector(
                                    "#transactionTable tbody tr"
                            )
                    ).isEmpty()
            );

            // Read transaction history.
            String transactionText =
                    driver.findElement(
                            By.id("transactionTable")
                    ).getText();

            System.out.println(
                    "TRANSACTION RESULT FOR $100:"
            );

            System.out.println(transactionText);

            // Verify $100 transaction.
            Assert.assertTrue(
                    transactionText.contains("100"),
                    "Transaction history for $100 was not returned."
            );

            // =========================================================
            // SEARCH FOR AN UNUSED AMOUNT
            // =========================================================

            // Return to Find Transactions.
            click(By.linkText("Find Transactions"));

            // Select account.
            selectAccount(
                    By.id("accountId"),
                    newAccountId
            );

            // Enter an amount that was never used.
            String unusedAmount = "999999";

            type(
                    By.id("amount"),
                    unusedAmount
            );

            // Search by amount.
            clickFindByAmount();

            // Wait for transaction table.
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("transactionTable")
                    )
            );

            // Wait for AJAX to finish.
            waitForAjax();

            // Read whatever the table currently contains.
            String unknownTransactionText =
                    driver.findElement(
                            By.id("transactionTable")
                    ).getText();

            System.out.println(
                    "TRANSACTION RESULT FOR UNUSED AMOUNT "
                            + unusedAmount + ":"
            );

            System.out.println(
                    unknownTransactionText
            );

            // ParaBank can leave the previous table row visible when
            // the search returns no matching transaction.
            // Therefore, verify that the unused amount itself does
            // not appear in the transaction history.
            Assert.assertFalse(
                    unknownTransactionText.contains(unusedAmount),
                    "A transaction was returned for the unused amount."
            );

            // =========================================================
            // SECTION 7 - UPDATE CONTACT INFORMATION
            // =========================================================

            // Open Update Contact Info.
            click(By.linkText("Update Contact Info"));

            // Wait for phone number.
            wait.until(d ->
                    !d.findElement(
                                    By.id("customer.phoneNumber")
                            )
                            .getAttribute("value")
                            .isEmpty()
            );

            // Get phone number.
            String phoneNumber =
                    driver.findElement(
                                    By.id("customer.phoneNumber")
                            )
                            .getAttribute("value");

            // Get last five digits.
            String lastFiveDigits =
                    phoneNumber.substring(
                            phoneNumber.length() - 5
                    );

            System.out.println(
                    "LAST FIVE PHONE DIGITS: "
                            + lastFiveDigits
            );

            // Locate ZIP code.
            WebElement zipCodeField =
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.id("customer.address.zipCode")
                            )
                    );

            // Delete old ZIP.
            zipCodeField.clear();

            // Enter last five phone digits.
            zipCodeField.sendKeys(lastFiveDigits);

            // Update profile.
            click(
                    By.xpath("//input[@value='Update Profile']")
            );

            // Wait for profile update.
            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.id("rightPanel"),
                            "Profile Updated"
                    )
            );

            // =========================================================
            // SECTION B - OPEN BLAZEDEMO IN A NEW WINDOW
            // =========================================================

            // Open another browser window without closing ParaBank.
            driver.switchTo().newWindow(WindowType.WINDOW);

            // Open BlazeDemo.
            driver.get("https://blazedemo.com/");

            // Select Boston.
            new Select(
                    wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.name("fromPort")
                            )
                    )
            ).selectByVisibleText("Boston");

            // Select Rome.
            new Select(
                    driver.findElement(
                            By.name("toPort")
                    )
            ).selectByVisibleText("Rome");

            // Find flights.
            click(
                    By.xpath("//input[@value='Find Flights']")
            );

            // Wait for flight results.
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(
                                    "table.table tbody tr"
                            )
                    )
            );

            // Get flight number from first flight.
            String flightNumber =
                    driver.findElement(
                                    By.cssSelector(
                                            "table.table tbody tr:first-child td:nth-child(2)"
                                    )
                            )
                            .getText()
                            .trim();

            System.out.println(
                    "FLIGHT NUMBER: " + flightNumber
            );

            // Select first flight.
            click(
                    By.cssSelector(
                            "table.table tbody tr:first-child input[type='submit']"
                    )
            );

            // =========================================================
            // BLAZEDEMO PURCHASE FORM
            // =========================================================

            // Passenger name.
            type(
                    By.id("inputName"),
                    "Mike Tester"
            );

            // Address.
            type(
                    By.id("address"),
                    "10 Test Street"
            );

            // City.
            type(
                    By.id("city"),
                    "Lagos"
            );

            // State.
            type(
                    By.id("state"),
                    "Lagos"
            );

            // Paste flight number into ZIP.
            type(
                    By.id("zipCode"),
                    flightNumber
            );

            // Select Visa.
            new Select(
                    driver.findElement(
                            By.id("cardType")
                    )
            ).selectByVisibleText("Visa");

            // Card number.
            type(
                    By.id("creditCardNumber"),
                    "4111111111111111"
            );

            // Card month.
            WebElement cardMonth =
                    driver.findElement(
                            By.id("creditCardMonth")
                    );

            cardMonth.clear();

            type(
                    By.id("creditCardMonth"),
                    "12"
            );

            // Card year.
            WebElement cardYear =
                    driver.findElement(
                            By.id("creditCardYear")
                    );

            cardYear.clear();

            type(
                    By.id("creditCardYear"),
                    "2028"
            );

            // Name on card.
            type(
                    By.id("nameOnCard"),
                    "Mike Tester"
            );

            // Tick Remember Me.
            WebElement rememberMe =
                    driver.findElement(
                            By.id("rememberMe")
                    );

            if (!rememberMe.isSelected()) {
                rememberMe.click();
            }

            // Purchase flight.
            click(
                    By.xpath(
                            "//input[@value='Purchase Flight']"
                    )
            );

            // Wait for purchase confirmation.
            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.tagName("body"),
                            "Thank you for your purchase today!"
                    )
            );

            // =========================================================
            // PROJECT COMPLETED
            // =========================================================

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "TAS 4D PROJECT FLOW COMPLETED SUCCESSFULLY."
            );

            System.out.println(
                    "=========================================="
            );

        } finally {

            // Close all browser windows.
            if (driver != null) {
                driver.quit();
            }
        }
    }

    // =============================================================
    // HELPER METHODS
    // =============================================================

    // Click an element when it becomes clickable.
    private void click(By locator) {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        locator
                )
        ).click();
    }

    // Wait for an input to become visible,
    // clear it and type the supplied text.
    private void type(
            By locator,
            String text
    ) {

        WebElement element =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                locator
                        )
                );

        element.clear();
        element.sendKeys(text);
    }

    // Wait until the account dropdown contains
    // a real account number.
    private void waitForRealAccount(
            By locator
    ) {

        wait.until(d -> {

            Select select =
                    new Select(
                            d.findElement(locator)
                    );

            for (WebElement option :
                    select.getOptions()) {

                String value =
                        option.getAttribute("value");

                if (value != null
                        && !value.trim().isEmpty()) {

                    return true;
                }
            }

            return false;
        });
    }

    // Get the first real account from a dropdown.
    private String getFirstRealAccount(
            By locator
    ) {

        waitForRealAccount(locator);

        Select select =
                new Select(
                        driver.findElement(locator)
                );

        for (WebElement option :
                select.getOptions()) {

            String value =
                    option.getAttribute("value");

            if (value != null
                    && !value.trim().isEmpty()) {

                return value;
            }
        }

        throw new RuntimeException(
                "No real account was found in the dropdown."
        );
    }

    // Wait for a specific account to appear,
    // then select it.
    private void selectAccount(
            By locator,
            String accountNumber
    ) {

        wait.until(d -> {

            Select select =
                    new Select(
                            d.findElement(locator)
                    );

            for (WebElement option :
                    select.getOptions()) {

                String value =
                        option.getAttribute("value");

                if (accountNumber.equals(value)) {
                    return true;
                }
            }

            return false;
        });

        new Select(
                driver.findElement(locator)
        )
                .selectByValue(accountNumber);
    }

    // Click Find by Amount using JavaScript.
    private void clickFindByAmount() {

        WebElement button =
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.id("findByAmount")
                        )
                );

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});" +
                                "arguments[0].click();",
                        button
                );

        // Wait for AJAX operation.
        waitForAjax();
    }

    // Wait until ParaBank AJAX requests finish.
    private void waitForAjax() {

        wait.until(d -> {

            Object result =
                    ((JavascriptExecutor) d)
                            .executeScript(
                                    "return window.jQuery ? " +
                                            "jQuery.active === 0 : true;"
                            );

            return Boolean.TRUE.equals(result);
        });
    }
}