package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class CheckAttendanceTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void loginTest() {
        driver.get(ConfigReader.get("URL"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.get("USERNAME"));
        loginPage.enterPassword(ConfigReader.get("PASSWORD"));
        loginPage.clickLogin();

    }

    @Test(priority = 2)
    public void checkAttendance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.partialLinkText("Employee")));
        element.click();
    }

    @Test(priority = 3)
    public void filterAttendance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='page-List/Employee/List']/div[1]/div/div/div[2]/div[2]/div[1]/button")));
        addButton.click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
