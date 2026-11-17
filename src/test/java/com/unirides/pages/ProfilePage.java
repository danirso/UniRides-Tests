package com.unirides.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final String PROFILE_PAGE_ID = "screen_profile";
    private static final String LOGOUT_BUTTON_ID  = "logout_tile";

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement profilePage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + PROFILE_PAGE_ID + "\")")));
    }

    private WebElement logoutButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + LOGOUT_BUTTON_ID + "\")")));
    }

    public void tapLogoutButton() { logoutButton().click(); }

    public boolean isDisplayed() {
        try {
            return profilePage().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
