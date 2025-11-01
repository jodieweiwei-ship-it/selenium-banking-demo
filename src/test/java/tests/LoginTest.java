package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");
        
        Assert.assertTrue(dashboard.isWelcomeMessageDisplayed(), 
            "Welcome To Manager's Page of Guru99 Bank");
        Assert.assertTrue(dashboard.getPageTitle().contains("Guru99 Bank"), 
            "Page title should contain 'Guru99 Bank'");
    }

    @Test(priority = 2, description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserId("invalid_user");
        loginPage.enterPassword("invalid_pass");
        loginPage.clickLogin();

        // Accept alert that appears on invalid login
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            // Alert might not appear, continue
        }

        // Verify still on login page
        Assert.assertTrue(driver.getCurrentUrl().contains("V4"), 
            "Should remain on login page after invalid credentials");
    }

    @Test(priority = 3, description = "Verify login with empty credentials")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();

        // Guru99 shows alert for empty fields
        try {
            String alertText = driver.switchTo().alert().getText();
            Assert.assertTrue(alertText.contains("not valid") || alertText.contains("must not be blank"),
                "Alert should mention User-ID requirement");
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            Assert.fail("Expected alert for empty credentials");
        }
    }
}
