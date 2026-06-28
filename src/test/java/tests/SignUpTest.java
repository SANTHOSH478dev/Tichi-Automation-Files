package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignUpPage;
import utils.ConfigReader;

public class SignUpTest extends BaseTest {

    private void goToSignUp() {
        driver.get(ConfigReader.get("url") + "/signup");
    }

    // TC-SU-01: Successful Registration
    @Test(priority = 1, description = "TC-SU-01: Verify new user can register with valid inputs")
    public void testSuccessfulRegistration() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterName("John Doe");
        signUpPage.enterEmail("johndoe_" + System.currentTimeMillis() + "@example.com");
        signUpPage.enterPassword("Test@1234");
        signUpPage.enterConfirmPassword("Test@1234");
        signUpPage.clickSignUp();
        Assert.assertTrue(signUpPage.isRegistrationSuccessful(),
                "TC-SU-01 FAILED: Registration did not succeed");
    }

    // TC-SU-02: Registration with Already Registered Email
    @Test(priority = 2, description = "TC-SU-02: Verify duplicate email registration is blocked")
    public void testRegistrationWithExistingEmail() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterName("John Doe");
        signUpPage.enterEmail(ConfigReader.get("email"));
        signUpPage.enterPassword("Test@1234");
        signUpPage.enterConfirmPassword("Test@1234");
        signUpPage.clickSignUp();
        String error = signUpPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-SU-02 FAILED: No error shown for duplicate email registration");
    }

    // TC-SU-03: Registration with Invalid Email Format
    @Test(priority = 3, description = "TC-SU-03: Verify invalid email format is rejected")
    public void testRegistrationWithInvalidEmail() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterName("John Doe");
        signUpPage.enterEmail("userexample.com");
        signUpPage.enterPassword("Test@1234");
        signUpPage.enterConfirmPassword("Test@1234");
        signUpPage.clickSignUp();
        String error = signUpPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-SU-03 FAILED: No error shown for invalid email format");
    }

    // TC-SU-04: Registration with All Fields Empty
    @Test(priority = 4, description = "TC-SU-04: Verify empty form submission is blocked")
    public void testRegistrationWithAllFieldsEmpty() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.clickSignUp();
        String error = signUpPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-SU-04 FAILED: No validation errors shown for empty form submission");
    }

    // TC-SU-05: Registration with Weak Password
    @Test(priority = 5, description = "TC-SU-05: Verify weak password is rejected")
    public void testRegistrationWithWeakPassword() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterName("John Doe");
        signUpPage.enterEmail("johndoe@example.com");
        signUpPage.enterPassword("123");
        signUpPage.enterConfirmPassword("123");
        signUpPage.clickSignUp();
        String error = signUpPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-SU-05 FAILED: No error shown for weak password");
    }

    // TC-SU-06: Password and Confirm Password Mismatch
    @Test(priority = 6, description = "TC-SU-06: Verify mismatched passwords are rejected")
    public void testPasswordMismatch() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterName("John Doe");
        signUpPage.enterEmail("johndoe@example.com");
        signUpPage.enterPassword("Test@1234");
        signUpPage.enterConfirmPassword("Test@5678");
        signUpPage.clickSignUp();
        String error = signUpPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-SU-06 FAILED: No error shown for password mismatch");
    }

    // TC-SU-07: Name Field Accepts Spaces Only
    @Test(priority = 7, description = "TC-SU-07: Verify whitespace-only name is rejected")
    public void testNameWithSpacesOnly() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterName("     ");
        signUpPage.enterEmail("johndoe@example.com");
        signUpPage.enterPassword("Test@1234");
        signUpPage.enterConfirmPassword("Test@1234");
        signUpPage.clickSignUp();
        String error = signUpPage.getErrorMessage();
        Assert.assertFalse(error.isEmpty(),
                "TC-SU-07 FAILED: No error shown for spaces-only name");
    }

    // TC-SU-08: Password Field Masking
    @Test(priority = 8, description = "TC-SU-08: Verify password field is masked by default on Sign Up")
    public void testPasswordMasking() {
        goToSignUp();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.enterPassword("Test@1234");
        String type = signUpPage.getPasswordFieldType();
        Assert.assertEquals(type, "password",
                "TC-SU-08 FAILED: Password field is not masked on Sign Up page (type=" + type + ")");
    }
}
