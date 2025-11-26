package tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;

public class DashboardTest extends BaseTest {
    String email = "test" + new Random().nextInt(10000) + "@example.com";

    @Test(priority = 1, description = "Verify successfully logout")
    public void testLogout() {
        DashboardPage dashboard = loginToDashboard();

        // Verify dashboard is displayed
        Assert.assertTrue(dashboard.isWelcomeMessageDisplayed(),
                "Dashboard should be displayed after login");

        // Logout
        dashboard.clickLogout();

        // Verify returned to login page
        Assert.assertTrue(driver.getCurrentUrl().contains("V4"),
                "Should return to login page after logout");

    }

    @Test(priority = 2, description = "Verify page title")
    public void testPageTitle() {
        DashboardPage dashboard = loginToDashboard();

        // Verify dashboard is displayed
        Assert.assertTrue(dashboard.isWelcomeMessageDisplayed(),
                "Dashboard should be displayed after login");
        String pageTitle = dashboard.getPageTitle();
        // System.out.print(pageTitle);
        Assert.assertTrue(pageTitle.equals("Guru99 Bank Manager HomePage"), "page title is not correct");
    }

    @Test(priority = 3, description = "Verify managerID")
    public void testmanagerID() {
        DashboardPage dashboard = loginToDashboard();

        // Verify dashboard is displayed
        Assert.assertTrue(dashboard.isWelcomeMessageDisplayed(),
                "Dashboard should be displayed after login");
        String managerID = dashboard.getManagerId();
        // System.out.print(managerID);
        Assert.assertTrue(managerID.contains(loginUsername), "managerID is not correct");
    }

    @Test(priority = 4, description = "verify NeW Customer button")
    public void neWCustomerButton() {
        DashboardPage dashboard = loginToDashboard();
        String addNewCustomer = dashboard.getAddNewCustomerForm();
        // System.out.println(addNewCustomer);
        Assert.assertTrue(addNewCustomer.equals("Add New Customer"), "Add New Customer form should display");
    }

    @Test(priority = 5, description = "fill Add New Customer form and verify submition successfully")
    public void fillAddNewCustomerFormAndSubmit() {
        DashboardPage dashboard = loginToDashboard();
        dashboard.getAddNewCustomerForm();
        dashboard.fillAddNewCustomerForm("Wei Wei", "female", "001994-03-07", "68 halstead dr", "Markham", "ON",
                "123456", "6479146937", email, "rarehup");
        dashboard.newAccountSubmit();
        String customerRegisteredSuccessfully = dashboard.checkCustomerRegistered();
        Assert.assertTrue(customerRegisteredSuccessfully.equals("Customer Registered Successfully!!!"),
                "faled for filling Add New Customer form and verifying submition");
    }
}
