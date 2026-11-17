package com.unirides.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final String EMAIL_INPUT_ID = "login_email_input";
    private static final String PASSWORD_INPUT_ID = "password_input";
    private static final String SUBMIT_BUTTON_ID = "login_submit_button";
    private static final String GO_TO_REGISTER_ID = "login_to_register_button";

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement emailInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + EMAIL_INPUT_ID + "\")")));
    }

    private WebElement passwordInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + PASSWORD_INPUT_ID + "\")")));
    }

    private WebElement submitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + SUBMIT_BUTTON_ID + "\")")));
    }

    public void enterEmail(String email) {
        emailInput().click();
        emailInput().sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput().click();
        passwordInput().sendKeys(password);
    }

    public void tapSubmit() {
        submitButton().click();
    }

    public void loginAs(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        tapSubmit();
    }

    public boolean isErrorDisplayed(String message) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.accessibilityId(message)
            )).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isDisplayed() {
        try {
            return emailInput().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}