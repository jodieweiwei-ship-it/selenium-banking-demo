package tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {
	String email = "test" + new Random().nextInt(10000) + "@example.com";

@Test(priority=1,description="Verify successfully logout")
	public void testLogout() {
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");
    
    // Verify dashboard is displayed
    Assert.assertTrue(dashboard.isWelcomeMessageDisplayed(), 
        "Dashboard should be displayed after login");
    
    // Logout
    dashboard.logout();
    
    // Verify returned to login page
    Assert.assertTrue(driver.getCurrentUrl().contains("V4"), 
        "Should return to login page after logout");

	}
@Test(priority = 2,description="Verify page title")
public void testPageTitle() {
 LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");
    
    // Verify dashboard is displayed
    Assert.assertTrue(dashboard.isWelcomeMessageDisplayed(), 
        "Dashboard should be displayed after login");
    String pageTitle=dashboard.getPageTitle();
    //System.out.print(pageTitle);
    Assert.assertTrue(pageTitle.equals("Guru99 Bank Manager HomePage"),"page title is not correct");
}
@Test(priority = 3,description="Verify managerID")
	public void testmanagerID() {
		LoginPage loginPage = new LoginPage(driver);
		DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");
	    
	    // Verify dashboard is displayed
	    Assert.assertTrue(dashboard.isWelcomeMessageDisplayed(), 
	        "Dashboard should be displayed after login");
	    String managerID=dashboard.getManagerId();
	    //System.out.print(managerID);
	    Assert.assertTrue(managerID.contains("mngr644332"),"managerID is not correct");
	}
@Test(priority = 4,description="verify NeW Customer button")
	public void neWCustomerButton() {
		LoginPage loginPage = new LoginPage(driver);
	    DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");
	    String addNewCustomer=dashboard.getAddNewCustomerForm();
	    //System.out.println(addNewCustomer);
	    Assert.assertTrue(addNewCustomer.equals("Add New Customer"),"Add New Customer form should display");
	}
@Test(priority = 5,description="fill Add New Customer form and verify submition successfully")
	public void fillAddNewCustomerFormAndSubmit() {
	LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboard = loginPage.login("mngr644332", "rarehup");
    dashboard.getAddNewCustomerForm();
    dashboard.fillAddNewCustomerForm("Wei Wei","female","001994-03-07","68 halstead dr","Markham","ON","123456","6479146937",email,"rarehup");
    dashboard.submit();
    String customerRegisteredSuccessfully=dashboard.checkCustomerRegistered();
    Assert.assertTrue(customerRegisteredSuccessfully.equals("Customer Registered Successfully!!!"),"faled for filling Add New Customer form and verifying submition");
}
}
