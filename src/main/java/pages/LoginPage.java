package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By emailField     = By.cssSelector("input[type='email'], input[name='email']");
    private By passwordField  = By.cssSelector("input[type='password'], input[name='password']");
    private By loginButton    = By.cssSelector("button[type='submit'], button.login-btn");
    private By continueButton = By.cssSelector("button[type='submit'], button.continue-btn");
    private By errorMessage   = By.cssSelector(".error-message, .alert, [class*='error'], [class*='invalid']");
    private By eyeIcon        = By.cssSelector("button.eye-icon, [class*='eye'], [class*='toggle-password']");
    private By forgotPassword = By.linkText("Forgot Password?");
    private By signUpLink     = By.linkText("Sign Up");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        field.clear();
        field.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickEyeIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(eyeIcon)).click();
    }

    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }

    public void clickForgotPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPassword)).click();
    }

    public void clickSignUpLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isEmailFieldDisplayed() {
        return driver.findElement(emailField).isDisplayed();
    }
}
