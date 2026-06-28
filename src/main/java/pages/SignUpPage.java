package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By nameField            = By.cssSelector("input[name='name'], input[placeholder*='name' i]");
    private By emailField           = By.cssSelector("input[type='email'], input[name='email']");
    private By passwordField        = By.cssSelector("input[name='password'], input[type='password']");
    private By confirmPasswordField = By.cssSelector("input[name='confirmPassword'], input[placeholder*='confirm' i]");
    private By signUpButton         = By.cssSelector("button[type='submit']");
    private By errorMessage         = By.cssSelector(".error-message, [class*='error'], [class*='invalid'], .alert");
    private By successMessage       = By.cssSelector(".success-message, [class*='success']");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterName(String name) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        field.clear();
        field.sendKeys(name);
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

    public void enterConfirmPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
        field.clear();
        field.sendKeys(password);
    }

    public void clickSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isRegistrationSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("dashboard") || driver.getCurrentUrl().contains("home");
        }
    }

    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }
}
