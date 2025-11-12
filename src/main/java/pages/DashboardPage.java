package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By welcomeMessage = By.cssSelector("marquee.heading3");
    private By managerIdLabel = By.cssSelector("tr.heading3 td");
    public By logoutLink = By.linkText("Log out");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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
    	  try {
    		  WebElement logoutElement = wait.until(ExpectedConditions.presenceOfElementLocated(logoutLink));
              
              // Scroll to element first
              ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutElement);
              Thread.sleep(500); // Small pause after scroll
              
              // Try regular click first
              try {
                  wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
              } catch (Exception e) {
                  // If regular click fails, use JavaScript click
                  System.out.println("Regular click failed, using JavaScript click");
                  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutElement);
              }
              
              // Handle logout success alert
              wait.until(ExpectedConditions.alertIsPresent());
              driver.switchTo().alert().accept();
    	    } catch (Exception e) {
    	        System.err.println("Error during logout: " + e.getMessage());
    	        throw new RuntimeException("Logout failed", e);
    	    }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
