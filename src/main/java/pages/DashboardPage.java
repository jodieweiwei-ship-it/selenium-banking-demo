package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    // Locators
    private By welcomeMessage = By.cssSelector("marquee.heading3");
    private By managerIdLabel = By.cssSelector("tr.heading3 td");
    private By logoutLink = By.linkText("Log out");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isWelcomeMessageDisplayed() {
        try {
            return driver.findElement(welcomeMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getManagerId() {
        return driver.findElement(managerIdLabel).getText();
    }

    public void logout() {
        driver.findElement(logoutLink).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
