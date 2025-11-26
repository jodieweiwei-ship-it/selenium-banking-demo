package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.DriverManager;
import pages.DashboardPage;
import pages.LoginPage;

public class BaseTest {
    protected WebDriver driver;
    protected String loginUsername = "mngr647463";
    protected String loginPw = "EtUgYbU";
    protected String baseUrl = "https://demo.guru99.com/V4/";

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        DriverManager.initDriver(browser);
        driver = DriverManager.getDriver();
        driver.get(baseUrl);
    }

    /**
     * Helper method to perform login and return DashboardPage
     * 
     * @return DashboardPage object after successful login
     */
    protected DashboardPage loginToDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.login(loginUsername, loginPw);
    }

    /**
     * Helper method to perform login with custom credentials
     * 
     * @param username Custom username
     * @param password Custom password
     * @return DashboardPage object after successful login
     */
    protected DashboardPage loginToDashboard(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.login(username, password);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}