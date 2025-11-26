package tests;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;

/**
 * Balance Enquiry Test Suite
 * Tests balance enquiry functionality for different scenarios
 */
public class BalanceEnquiryTest extends BaseTest {

    @Test(priority = 1, description = "Verify account number field validation - cannot be empty")
    public void testAccountNumberRequiredValidation() {
        DashboardPage dashboard=loginToDashboard();
        dashboard.navigateToBalanceEnquiry();
        Assert.assertTrue(driver.getTitle().contains("Balance Enquiry"), "Should navigate to Balance Enquiry page");
        // Click on account number field and tab out to trigger validation
        dashboard.triggerBalanceEnquiryAccountNoValidation();
        dashboard.submitBalanceEnquiry();
        // Verify validation message
        String validationMsg = dashboard.getVlidationMsg();
        Assert.assertTrue(validationMsg.contains("must not be blank") ||
                validationMsg.toLowerCase().contains("required"),
                "Account number field should be required");
    }

    // @Test(priority = 2, description = "Verify account number accepts only numeric
    // values")
    // public void testAccountNumberNumericValidation() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // driver.findElement(By.linkText("Balance Enquiry")).click();

    // // Try to enter alphabetic characters
    // driver.findElement(By.name("accountno")).sendKeys("abcd");
    // driver.findElement(By.name("accountno")).sendKeys(org.openqa.selenium.Keys.TAB);

    // // Verify validation message
    // String validationMsg = driver.findElement(By.id("message2")).getText();
    // Assert.assertTrue(validationMsg.contains("Characters are not allowed") ||
    // validationMsg.contains("not allowed"),
    // "Account number should only accept numeric values");
    // }

    // @Test(priority = 3, description = "Verify account number does not accept
    // special characters")
    // public void testAccountNumberSpecialCharValidation() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // driver.findElement(By.linkText("Balance Enquiry")).click();

    // // Try to enter special characters
    // driver.findElement(By.name("accountno")).sendKeys("123@#");
    // driver.findElement(By.name("accountno")).sendKeys(org.openqa.selenium.Keys.TAB);

    // // Verify validation message
    // String validationMsg = driver.findElement(By.id("message2")).getText();
    // Assert.assertTrue(validationMsg.contains("Special characters are not
    // allowed") ||
    // validationMsg.contains("not allowed"),
    // "Account number should not accept special characters");
    // }

    // @Test(priority = 4, description = "Verify error message for non-existent
    // account number")
    // public void testBalanceEnquiryNonExistentAccount() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // driver.findElement(By.linkText("Balance Enquiry")).click();

    // // Enter non-existent account number
    // driver.findElement(By.name("accountno")).sendKeys("999999999");
    // driver.findElement(By.name("AccSubmit")).click();

    // // Verify error message
    // try {
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    // wait.until(ExpectedConditions.alertIsPresent());
    // String alertText = driver.switchTo().alert().getText();
    // Assert.assertTrue(alertText.contains("does not exist") ||
    // alertText.toLowerCase().contains("not exist"),
    // "Should show error for non-existent account");
    // driver.switchTo().alert().accept();
    // } catch (Exception e) {
    // // Check page for error message
    // Assert.assertTrue(driver.getPageSource().contains("does not exist"),
    // "Should display error for invalid account number");
    // }
    // }

    // @Test(priority = 5, description = "Verify balance details displayed for valid
    // account")
    // public void testBalanceEnquiryDisplaysDetails() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // // Create customer and account first
    // String email = "balance_test" + new Random().nextInt(100000) +
    // "@example.com";
    // dashboard.getAddNewCustomerForm();
    // dashboard.fillAddNewCustomerForm("Balance Test User", "male", "04/12/1988",
    // "500 Balance Blvd", "BalanceCity", "BalanceState", "555555", "4444444444",
    // email, "Bal@123");
    // dashboard.submit();

    // try {
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // String custId = driver.findElement(By.xpath("//td[text()='Customer
    // ID']/following-sibling::td")).getText();

    // // Create account
    // driver.findElement(By.linkText("New Account")).click();
    // driver.findElement(By.name("cusid")).sendKeys(custId);
    // Select accountType = new Select(driver.findElement(By.name("selaccount")));
    // accountType.selectByVisibleText("Savings");
    // driver.findElement(By.name("inideposit")).sendKeys("8000");
    // driver.findElement(By.name("button2")).click();

    // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Account
    // ID']")));
    // String accountId = driver.findElement(By.xpath("//td[text()='Account
    // ID']/following-sibling::td"))
    // .getText();

    // // Check balance
    // driver.findElement(By.linkText("Balance Enquiry")).click();
    // driver.findElement(By.name("accountno")).sendKeys(accountId);
    // driver.findElement(By.name("AccSubmit")).click();

    // // Verify balance details are displayed
    // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".heading3")));
    // String balanceHeading =
    // driver.findElement(By.cssSelector(".heading3")).getText();
    // Assert.assertTrue(balanceHeading.contains("Balance Details") ||
    // balanceHeading.toLowerCase().contains("balance"),
    // "Balance details should be displayed");

    // } catch (Exception e) {
    // System.out.println("Balance details test: " + e.getMessage());
    // }
    // }

    // @Test(priority = 6, description = "Verify balance amount matches initial
    // deposit")
    // public void testBalanceMatchesInitialDeposit() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // String email = "match_test" + new Random().nextInt(100000) + "@example.com";
    // String initialDeposit = "12500";

    // dashboard.getAddNewCustomerForm();
    // dashboard.fillAddNewCustomerForm("Match Test User", "female", "06/30/1995",
    // "600 Match Dr", "MatchCity", "MatchState", "666666", "5555555555",
    // email, "Match@123");
    // dashboard.submit();

    // try {
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // String custId = driver.findElement(By.xpath("//td[text()='Customer
    // ID']/following-sibling::td")).getText();

    // driver.findElement(By.linkText("New Account")).click();
    // driver.findElement(By.name("cusid")).sendKeys(custId);
    // Select accountType = new Select(driver.findElement(By.name("selaccount")));
    // accountType.selectByVisibleText("Savings");
    // driver.findElement(By.name("inideposit")).sendKeys(initialDeposit);
    // driver.findElement(By.name("button2")).click();

    // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Account
    // ID']")));
    // String accountId = driver.findElement(By.xpath("//td[text()='Account
    // ID']/following-sibling::td"))
    // .getText();

    // driver.findElement(By.linkText("Balance Enquiry")).click();
    // driver.findElement(By.name("accountno")).sendKeys(accountId);
    // driver.findElement(By.name("AccSubmit")).click();

    // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Balance']")));
    // String balance =
    // driver.findElement(By.xpath("//td[text()='Balance']/following-sibling::td")).getText();

    // Assert.assertTrue(balance.contains(initialDeposit),
    // "Balance should match the initial deposit amount");

    // } catch (Exception e) {
    // System.out.println("Balance match test: " + e.getMessage());
    // }
    // }

    // @Test(priority = 7, description = "Verify account type displayed in balance
    // enquiry")
    // public void testAccountTypeDisplayedInBalanceEnquiry() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // String email = "type_test" + new Random().nextInt(100000) + "@example.com";

    // dashboard.getAddNewCustomerForm();
    // dashboard.fillAddNewCustomerForm("Type Test User", "male", "08/20/1987",
    // "700 Type Terrace", "TypeCity", "TypeState", "777777", "6666666666",
    // email, "Type@123");
    // dashboard.submit();

    // try {
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // String custId = driver.findElement(By.xpath("//td[text()='Customer
    // ID']/following-sibling::td")).getText();

    // driver.findElement(By.linkText("New Account")).click();
    // driver.findElement(By.name("cusid")).sendKeys(custId);
    // Select accountType = new Select(driver.findElement(By.name("selaccount")));
    // accountType.selectByVisibleText("Current");
    // driver.findElement(By.name("inideposit")).sendKeys("15000");
    // driver.findElement(By.name("button2")).click();

    // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Account
    // ID']")));
    // String accountId = driver.findElement(By.xpath("//td[text()='Account
    // ID']/following-sibling::td"))
    // .getText();

    // driver.findElement(By.linkText("Balance Enquiry")).click();
    // driver.findElement(By.name("accountno")).sendKeys(accountId);
    // driver.findElement(By.name("AccSubmit")).click();

    // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Type
    // of Account']")));
    // String accType = driver.findElement(By.xpath("//td[text()='Type of
    // Account']/following-sibling::td"))
    // .getText();

    // Assert.assertEquals(accType, "Current", "Account type should be displayed as
    // Current");

    // } catch (Exception e) {
    // System.out.println("Account type display test: " + e.getMessage());
    // }
    // }

    // @Test(priority = 8, description = "Verify reset button clears account number
    // field")
    // public void testBalanceEnquiryResetButton() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // driver.findElement(By.linkText("Balance Enquiry")).click();

    // // Enter account number
    // driver.findElement(By.name("accountno")).sendKeys("12345");

    // // Click reset
    // driver.findElement(By.name("res")).click();

    // // Verify field is cleared
    // String accountNoValue =
    // driver.findElement(By.name("accountno")).getAttribute("value");
    // Assert.assertTrue(accountNoValue.isEmpty(), "Reset button should clear
    // account number field");
    // }

    // @Test(priority = 9, description = "Verify submit button is enabled on Balance
    // Enquiry page")
    // public void testBalanceEnquirySubmitButtonEnabled() {
    // LoginPage loginPage = new LoginPage(driver);
    // DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");

    // driver.findElement(By.linkText("Balance Enquiry")).click();

    // // Verify submit button is present and enabled
    // org.openqa.selenium.WebElement submitButton =
    // driver.findElement(By.name("AccSubmit"));
    // Assert.assertTrue(submitButton.isDisplayed(), "Submit button should be
    // visible");
    // Assert.assertTrue(submitButton.isEnabled(), "Submit button should be
    // enabled");
    // }
}
