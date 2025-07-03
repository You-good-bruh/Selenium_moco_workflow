package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() {
        driver.get(ConfigReader.get("URL"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.get("USERNAME"));
        loginPage.enterPassword(ConfigReader.get("PASSWORD"));
        loginPage.clickLogin();

        // Optional: Add assertions here
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
