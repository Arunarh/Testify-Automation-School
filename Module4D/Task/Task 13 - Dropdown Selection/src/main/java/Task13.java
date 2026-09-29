import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Task13 {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {

            // Open the Demo Dropdown page
            driver.get(
                    "https://selenium08.blogspot.com/2019/11/dropdown.html"
            );

            driver.manage().window().maximize();

            // -------------------------------
            // Select Nigeria from Country list
            // -------------------------------

            WebElement countryDropdown =
                    driver.findElement(
                            By.name("country")
                    );

            Select country =
                    new Select(countryDropdown);

            country.selectByVisibleText("Nigeria");

            System.out.println(
                    "Selected Country: " +
                            country.getFirstSelectedOption().getText()
            );

            // -------------------------------
            // Select January, February and March
            // -------------------------------

            WebElement monthDropdown =
                    driver.findElement(
                            By.name("Month")
                    );

            Select months =
                    new Select(monthDropdown);

            months.selectByVisibleText("January");
            months.selectByVisibleText("February");
            months.selectByVisibleText("March");

            System.out.println("Selected Months:");

            for (WebElement option :
                    months.getAllSelectedOptions()) {

                System.out.println(option.getText());
            }

            System.out.println(
                    "Dropdown selections completed successfully."
            );

        } finally {

            driver.quit();
        }
    }
}