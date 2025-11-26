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
    private By logoutLink = By.linkText("Log out");
    private By NewCustomerButton = By.linkText("New Customer");
    private By AddNewCustomerLable = By.cssSelector(".heading3");
    private By newAccountSubmitButton = By.name("sub");
    private By newAccountCustomerName = By.name("name");
    private By newAccountGenderFemale = By.cssSelector("input[value=\"m\"]");
    private By newAccountGenderMale = By.cssSelector("input[value=\"f\"]");
    private By newAccountDOB = By.name("dob");
    private By newAccountAddress = By.name("addr");
    private By newAccountCity = By.name("city");
    private By newAccountState = By.name("state");
    private By newAccountPIN = By.name("pinno");
    private By newAccountMobileNo = By.name("telephoneno");
    private By newAccountEmail = By.name("emailid");
    private By newAccountPassword = By.name("password");
    private By customerRegisterSuccessfully = By.cssSelector("#customer .heading3");

    private By EditCustomerButton = By.linkText("Edit Customer");
    private By EditCustomerSubmitButton = By.name("AccSubmit");
    private By EditCustomerID = By.name("cusid");

    // Navigation Menu Links
    private By balanceEnquiryLink = By.linkText("Balance Enquiry");
    private By balanceEnquiryAccountNo = By.name("accountno");
    private By balanceEnquirySubmitButton = By.name("AccSubmit");
    private By balanceEnquiryValidationMsg = By.id("message2");
    // private By fundTransferLink = By.linkText("Fund Transfer");
    // private By miniStatementLink = By.linkText("Mini Statement");
    // private By customisedStatementLink = By.linkText("Customised Statement");
    // private By deleteCustomerLink = By.linkText("Delete Customer");
    // private By deleteAccountLink = By.linkText("Delete Account");
    // private By newAccountLink = By.linkText("New Account");
    // private By editAccountLink = By.linkText("Edit Account");
    // private By changePasswordLink = By.linkText("Change Password");
    // private By depositLink = By.linkText("Deposit");
    // private By withdrawalLink = By.linkText("Withdrawal");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitSubMenuIsClickableAndClick(By menuLocator) throws InterruptedException {

        WebElement menuButton = wait.until(ExpectedConditions.presenceOfElementLocated(menuLocator));

        // Scroll to element first
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuButton);
        Thread.sleep(2000); // Small pause after scroll

        // Try regular click first
        try {
            wait.until(ExpectedConditions.elementToBeClickable(menuLocator)).click();
        } catch (Exception e) {
            // If regular click fails, use JavaScript click
            System.out.println("Regular click failed, using JavaScript click");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuButton);
        }

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

    public void clickLogout() {

        try {
            waitSubMenuIsClickableAndClick(logoutLink);
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

    public String getAddNewCustomerForm() {
        driver.findElement(NewCustomerButton).click();
        return driver.findElement(AddNewCustomerLable).getText();
    }

    public void fillAddNewCustomerForm(String name, String gender, String DOB, String address, String city,
            String state, String PIN, String mobileNo, String email, String password) {
        driver.findElement(newAccountCustomerName).sendKeys(name);
        if (gender.toLowerCase().equals("female"))
            driver.findElement(newAccountGenderFemale).click();
        else
            driver.findElement(newAccountGenderMale).click();
        driver.findElement(newAccountDOB).sendKeys(DOB);
        driver.findElement(newAccountAddress).sendKeys(address);
        driver.findElement(newAccountCity).sendKeys(city);
        driver.findElement(newAccountState).sendKeys(state);
        driver.findElement(newAccountPIN).sendKeys(PIN);
        driver.findElement(newAccountMobileNo).sendKeys(mobileNo);
        driver.findElement(newAccountEmail).sendKeys(email);
        driver.findElement(newAccountPassword).sendKeys(password);

    }

    public void newAccountSubmit() {
        driver.findElement(newAccountSubmitButton).click();
    }

    public String checkCustomerRegistered() {
    	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(customerRegisterSuccessfully));
        return driver.findElement(customerRegisterSuccessfully).getText();
    }

    // ===== Navigation Methods =====
    /**
     * Navigate to Balance Enquiry page
     * 
     * @throws InterruptedException
     */
    public void navigateToBalanceEnquiry() {
        try {
            waitSubMenuIsClickableAndClick(balanceEnquiryLink);
            // Wait for Balance Enquiry page to load completely
            wait.until(ExpectedConditions.titleContains("Balance Enquiry"));
            // Also wait for the account number field to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(balanceEnquiryAccountNo));

        } catch (InterruptedException e) {
            System.err.println("Error during navigateToBalanceEnquiry: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Enter account number in Balance Enquiry form
     * 
     * @param accountNo The account number to check balance for
     */
    public void enterBalanceEnquiryAccountNo(String accountNo) {
        driver.findElement(balanceEnquiryAccountNo).sendKeys(accountNo);
    }

    /**
     * Click on the account number field and tab out to trigger validation
     */
    public void triggerBalanceEnquiryAccountNoValidation() {
        // WebElement accountField =
        // wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryAccountNo));

        driver.findElement(balanceEnquiryAccountNo).click();
        // driver.findElement(balanceEnquiryAccountNo).sendKeys(org.openqa.selenium.Keys.TAB);
    }

    /**
     * Click the submit button on Balance Enquiry form
     */
    public void submitBalanceEnquiry() {
        driver.findElement(balanceEnquirySubmitButton).click();
    }

    public String getVlidationMsg() {

        return driver.findElement(balanceEnquiryValidationMsg).getText();

    }

    /**
     * Navigate to Fund Transfer page
     */
    // public void navigateToFundTransfer() {
    // driver.findElement(fundTransferLink).click();
    // }

    // /**
    // * Navigate to Mini Statement page
    // */
    // public void navigateToMiniStatement() {
    // driver.findElement(miniStatementLink).click();
    // }

    // /**
    // * Navigate to Customised Statement page
    // */
    // public void navigateToCustomisedStatement() {
    // driver.findElement(customisedStatementLink).click();
    // }

    // /**
    // * Navigate to Delete Customer page
    // */
    // public void navigateToDeleteCustomer() {
    // driver.findElement(deleteCustomerLink).click();
    // }

    // /**
    // * Navigate to Delete Account page
    // */
    // public void navigateToDeleteAccount() {
    // driver.findElement(deleteAccountLink).click();
    // }

    // /**
    // * Navigate to New Account page
    // */
    // public void navigateToNewAccount() {
    // driver.findElement(newAccountLink).click();
    // }

    // /**
    // * Navigate to Edit Account page
    // */
    // public void navigateToEditAccount() {
    // driver.findElement(editAccountLink).click();
    // }

    // /**
    // * Navigate to Change Password page
    // */
    // public void navigateToChangePassword() {
    // driver.findElement(changePasswordLink).click();
    // }

    // /**
    // * Navigate to Deposit page
    // */
    // public void navigateToDeposit() {
    // driver.findElement(depositLink).click();
    // }

    // /**
    // * Navigate to Withdrawal page
    // */
    // public void navigateToWithdrawal() {
    // driver.findElement(withdrawalLink).click();
    // }
}
