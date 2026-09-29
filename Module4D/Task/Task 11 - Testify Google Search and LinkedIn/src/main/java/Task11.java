import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Task11 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(120));

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;


            // ==========================================
            // 1. GO TO GOOGLE
            // ==========================================

            driver.get("https://www.google.com");


            // ==========================================
            // 2. HANDLE GOOGLE COOKIE CONSENT
            // ==========================================

            try {

                WebElement rejectButton = new WebDriverWait(
                        driver,
                        Duration.ofSeconds(5)
                ).until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//button[normalize-space()='Reject all']"
                                )
                        )
                );

                rejectButton.click();

            } catch (Exception e) {

                System.out.println(
                        "Google cookie consent did not appear."
                );
            }


            // ==========================================
            // 3. SEARCH FOR "TESTIFY LTD"
            // ==========================================

            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.name("q")
                    )
            );

            searchBox.sendKeys("testify ltd");

            searchBox.submit();

            System.out.println(
                    "Google search completed."
            );

            System.out.println();

            System.out.println(
                    "If Google shows 'I'm not a robot', solve the CAPTCHA manually."
            );

            System.out.println(
                    "After solving it, leave the browser open."
            );

            System.out.println();


            // ==========================================
            // 4. WAIT FOR TESTIFY SEARCH RESULT
            // ==========================================

            WebElement testifyResult = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector(
                                    "a[href*='testifyltd.com']"
                            )
                    )
            );

            System.out.println(
                    "Testify result found."
            );


            // ==========================================
            // 5. CLICK TESTIFY LIMITED
            // ==========================================

            testifyResult.click();


            // ==========================================
            // 6. WAIT FOR TESTIFY WEBSITE
            // ==========================================

            wait.until(
                    ExpectedConditions.urlContains(
                            "testifyltd.com"
                    )
            );

            System.out.println(
                    "Testify website opened."
            );


            // ==========================================
            // 7. SCROLL TO THE BOTTOM
            // ==========================================

            js.executeScript(
                    "window.scrollTo(0, document.body.scrollHeight);"
            );

            Thread.sleep(3000);


            // ==========================================
            // 8. FIND LINKEDIN LINK USING JAVASCRIPT
            // ==========================================

            System.out.println();
            System.out.println(
                    "Looking for LinkedIn link..."
            );


            String linkedInHref = (String) js.executeScript(
                    "var links = document.querySelectorAll('a');" +
                            "for (var i = 0; i < links.length; i++) {" +
                            "    var href = links[i].href;" +
                            "    if (href && href.toLowerCase().includes('linkedin')) {" +
                            "        return href;" +
                            "    }" +
                            "}" +
                            "return null;"
            );


            // ==========================================
            // 9. CHECK WHETHER LINKEDIN WAS FOUND
            // ==========================================

            if (linkedInHref == null) {

                System.out.println(
                        "LinkedIn link was NOT found on the page."
                );

                System.out.println(
                        "Current page URL: "
                                + driver.getCurrentUrl()
                );

                throw new RuntimeException(
                        "Unable to find LinkedIn link on Testify website."
                );
            }


            System.out.println(
                    "LinkedIn link found:"
            );

            System.out.println(
                    linkedInHref
            );


            // ==========================================
            // 10. SAVE TESTIFY WINDOW
            // ==========================================

            String testifyWindow =
                    driver.getWindowHandle();


            // ==========================================
            // 11. CLICK LINKEDIN USING JAVASCRIPT
            // ==========================================

            js.executeScript(
                    "var links = document.querySelectorAll('a');" +
                            "for (var i = 0; i < links.length; i++) {" +
                            "    var href = links[i].href;" +
                            "    if (href && href.toLowerCase().includes('linkedin')) {" +
                            "        links[i].click();" +
                            "        break;" +
                            "    }" +
                            "}"
            );

            System.out.println(
                    "LinkedIn icon clicked."
            );


            // ==========================================
            // 12. WAIT FOR NEW WINDOW / TAB
            // ==========================================

            wait.until(
                    ExpectedConditions.numberOfWindowsToBe(2)
            );


            // ==========================================
            // 13. SWITCH TO LINKEDIN
            // ==========================================

            Set<String> windows =
                    driver.getWindowHandles();

            for (String window : windows) {

                if (!window.equals(testifyWindow)) {

                    driver.switchTo().window(window);

                    break;
                }
            }


            // ==========================================
            // 14. WAIT FOR LINKEDIN TO LOAD
            // ==========================================

            Thread.sleep(5000);

            System.out.println();

            System.out.println(
                    "LinkedIn page opened."
            );

            System.out.println(
                    "Page title: "
                            + driver.getTitle()
            );

            System.out.println(
                    "Current URL: "
                            + driver.getCurrentUrl()
            );


            // ==========================================
            // 15. GET LINKEDIN DESCRIPTION
            // ==========================================

            try {

                WebElement description =
                        wait.until(
                                ExpectedConditions
                                        .presenceOfElementLocated(
                                                By.cssSelector(
                                                        "meta[name='description']"
                                                )
                                        )
                        );

                String descriptionText =
                        description.getAttribute("content");

                System.out.println();

                System.out.println(
                        "===== TESTIFY LINKEDIN DESCRIPTION ====="
                );

                System.out.println(
                        descriptionText
                );

                System.out.println(
                        "========================================"
                );

            } catch (Exception e) {

                System.out.println(
                        "Meta description was not available."
                );

                System.out.println(
                        "LinkedIn page was successfully opened."
                );
            }


        } catch (Exception e) {

            e.printStackTrace();


        } finally {

            driver.quit();
        }
    }
}