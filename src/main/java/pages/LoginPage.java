package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators
    private By userIdField = By.name("uid");
    private By passwordField = By.name("password");
    private By loginButton = By.name("btnLogin");
    private By errorMessage = By.id("message23");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUserId(String userId) {
        WebElement userIdInput = driver.findElement(userIdField);
        userIdInput.clear();
        userIdInput.sendKeys(userId);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(passwordField);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public DashboardPage login(String userId, String password) {
        enterUserId(userId);
        enterPassword(password);
        clickLogin();
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}