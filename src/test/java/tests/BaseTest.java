package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.DriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://demo.guru99.com/V4/";
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome")String browser) {
        DriverManager.initDriver(browser);
        driver=DriverManager.getDriver();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}