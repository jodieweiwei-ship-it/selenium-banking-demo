package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://demo.guru99.com/V4/";

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}