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
    private By AddNewCustomerLable=By.cssSelector(".heading3");
    private By newAccountSubmitButton=By.name("sub");
    private By newAccountCustomerName=By.name("name");
    private By newAccountGenderFemale=By.cssSelector("input[value=\"m\"]");
    private By newAccountGenderMale=By.cssSelector("input[value=\"f\"]");
    private By newAccountDOB=By.name("dob");
    private By newAccountAddress=By.name("addr");
    private By newAccountCity=By.name("city");
    private By newAccountState=By.name("state");
    private By newAccountPIN=By.name("pinno");
    private By newAccountMobileNo=By.name("telephoneno");
    private By newAccountEmail=By.name("emailid");
    private By newAccountPassword=By.name("password");
    private By customerRegisterSuccessfully=By.cssSelector("#customer .heading3");

    private By EditCustomerButton = By.linkText("Edit Customer");
    private By EditCustomerSubmitButton=By.name("AccSubmit");
    private By EditCustomerID=By.name("cusid");

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
    public String getAddNewCustomerForm() {
    	driver.findElement(NewCustomerButton).click();
    	return driver.findElement(AddNewCustomerLable).getText();
    }

	public void fillAddNewCustomerForm(String name, String gender, String DOB, String address, String city,
			String state, String PIN, String mobileNo,String email, String password) {
		driver.findElement(newAccountCustomerName).sendKeys(name);
		if(gender.toLowerCase().equals("female"))
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

	public void submit() {
		driver.findElement(newAccountSubmitButton).click();
	}

	public String checkCustomerRegistered() {
		// TODO Auto-generated method stub
		return driver.findElement(customerRegisterSuccessfully).getText();
	}
}
