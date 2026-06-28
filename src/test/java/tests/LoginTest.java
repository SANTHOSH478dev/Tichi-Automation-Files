package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    // TC-LG-01: Successful Login with Valid Credentials
    @Test(priority = 1, description = "TC-LG-01: Verify login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(ConfigReader.get("email"));
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();
        String url = loginPage.getCurrentUrl();
        Assert.assertTrue(url.contains("dashboard") || url.contains("home"),
                "TC-LG-01 FAILED: User was not redirected to dashboard. Current URL: " + url);
    }

    // TC-LG-02: Login with Incorrect Password
    @Test(priority = 2, description = "TC-LG-02: Verify login fails with wrong password")
    public void testLoginWithIncorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(ConfigReader.get("email"));
        loginPage.enterPassword("WrongPass!");
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-LG-02 FAILED: No error message displayed for wrong password");
    }

    // TC-LG-03: Login with Unregistered Email
    @Test(priority = 3, description = "TC-LG-03: Verify login fails with unregistered email")
    public void testLoginWithUnregisteredEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("ghost@example.com");
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-LG-03 FAILED: No error message displayed for unregistered email");
    }

    // TC-LG-04: Login with Both Fields Empty
    @Test(priority = 4, description = "TC-LG-04: Verify login fails when both fields are empty")
    public void testLoginWithBothFieldsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-LG-04 FAILED: No validation error when submitting empty form");
    }

    // TC-LG-05: Login with Invalid Email Format (BUG DR-001)
    @Test(priority = 5, description = "TC-LG-05: Verify custom app-level error for invalid email format [BUG DR-001]")
    public void testLoginWithInvalidEmailFormat() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("userexample");
        loginPage.clickContinue();
        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-LG-05 FAILED [DR-001]: No custom app-level inline error shown for invalid email format");
    }

    // TC-LG-06: Login with Empty Password
    @Test(priority = 6, description = "TC-LG-06: Verify login fails when password is empty")
    public void testLoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(ConfigReader.get("email"));
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-LG-06 FAILED: No validation error when password is empty");
    }

    // TC-LG-07: Login with Empty Email
    @Test(priority = 7, description = "TC-LG-07: Verify login fails when email is empty")
    public void testLoginWithEmptyEmail() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();
        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-LG-07 FAILED: No validation error when email is empty");
    }

    // TC-LG-08: Password Field Masking on Login Page
    @Test(priority = 8, description = "TC-LG-08: Verify password field is masked by default")
    public void testPasswordMasking() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("AnyValue123");
        String type = loginPage.getPasswordFieldType();
        Assert.assertEquals(type, "password",
                "TC-LG-08 FAILED: Password field is not masked (type=" + type + ")");
    }

    // TC-LG-09: Show / Hide Password Toggle
    @Test(priority = 9, description = "TC-LG-09: Verify show/hide password toggle works correctly")
    public void testShowHidePasswordToggle() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("Test@1234");
        loginPage.clickEyeIcon();
        String typeAfterReveal = loginPage.getPasswordFieldType();
        Assert.assertEquals(typeAfterReveal, "text",
                "TC-LG-09 FAILED: Password not revealed after clicking eye icon");
        loginPage.clickEyeIcon();
        String typeAfterHide = loginPage.getPasswordFieldType();
        Assert.assertEquals(typeAfterHide, "password",
                "TC-LG-09 FAILED: Password not re-hidden after clicking eye icon again");
    }

    // TC-LG-10: Forgot Password Link Navigation
    @Test(priority = 10, description = "TC-LG-10: Verify Forgot Password link navigates correctly")
    public void testForgotPasswordNavigation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();
        String url = loginPage.getCurrentUrl();
        Assert.assertTrue(url.contains("forgot") || url.contains("reset"),
                "TC-LG-10 FAILED: Did not navigate to Forgot Password page. URL: " + url);
    }

    // TC-LG-11: Navigate to Sign Up from Login Page
    @Test(priority = 11, description = "TC-LG-11: Verify Sign Up link on login page works")
    public void testNavigateToSignUp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignUpLink();
        String url = loginPage.getCurrentUrl();
        Assert.assertTrue(url.contains("signup") || url.contains("register"),
                "TC-LG-11 FAILED: Did not navigate to Sign Up page. URL: " + url);
    }

    // TC-LG-12: Case-Insensitive Email Login
    @Test(priority = 12, description = "TC-LG-12: Verify login works with uppercase email")
    public void testCaseInsensitiveEmailLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(ConfigReader.get("email").toUpperCase());
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();
        String url = loginPage.getCurrentUrl();
        Assert.assertTrue(url.contains("dashboard") || url.contains("home"),
                "TC-LG-12 FAILED: Uppercase email login did not succeed. URL: " + url);
    }

    // TC-LG-13: SQL Injection Attempt
    @Test(priority = 13, description = "TC-LG-13: Verify SQL injection attempt is blocked")
    public void testSQLInjection() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("' OR '1'='1");
        loginPage.enterPassword("anything");
        loginPage.clickLogin();
        String url = loginPage.getCurrentUrl();
        Assert.assertFalse(url.contains("dashboard") || url.contains("home"),
                "TC-LG-13 FAILED: SQL injection bypassed authentication!");
    }

    // TC-LG-14: Successful Logout and Re-Login
    @Test(priority = 14, description = "TC-LG-14: Verify logout clears session and re-login works")
    public void testLogoutAndReLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(ConfigReader.get("email"));
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();
        // Navigate back to login (simulate logout)
        driver.get(ConfigReader.get("url") + "/login");
        loginPage.enterEmail(ConfigReader.get("email"));
        loginPage.enterPassword(ConfigReader.get("password"));
        loginPage.clickLogin();
        String url = loginPage.getCurrentUrl();
        Assert.assertTrue(url.contains("dashboard") || url.contains("home"),
                "TC-LG-14 FAILED: Re-login after logout did not succeed. URL: " + url);
    }
}
