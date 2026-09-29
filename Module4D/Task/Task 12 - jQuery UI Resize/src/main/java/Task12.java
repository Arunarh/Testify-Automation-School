import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Task12 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            // Go to jQuery UI
            driver.get("https://jqueryui.com/");

            // Click Resizable from the left menu
            driver.findElement(
                    By.linkText("Resizable")
            ).click();

            // Switch into the demo iframe
            WebElement iframe =
                    driver.findElement(
                            By.className("demo-frame")
                    );

            driver.switchTo().frame(iframe);

            // Find the resize handle
            WebElement resizeHandle =
                    driver.findElement(
                            By.cssSelector(".ui-resizable-se")
                    );

            // Scroll the resize handle into view
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            resizeHandle
                    );

            // Create Actions object
            Actions actions = new Actions(driver);

            // Drag the bottom-right handle to make the box bigger
            actions.dragAndDropBy(
                    resizeHandle,
                    50,
                    50
            ).perform();

            System.out.println(
                    "Box resized successfully."
            );

        } finally {

            driver.quit();
        }
    }
}