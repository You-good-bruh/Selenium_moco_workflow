package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Optional for CI
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("URL"));
    }

    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(ConfigReader.get("USERNAME"));
        loginPage.enterPassword(ConfigReader.get("PASSWORD"));
        loginPage.clickLogin();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
