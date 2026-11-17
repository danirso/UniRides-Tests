package com.unirides.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private static final String MAIN_PAGE_ID = "screen_main";
    private static final String SEARCH_RIDES_BUTTON_ID  = "nav_search_key";
    private static final String LIST_RIDES_BUTTON_ID  = "nav_rides_key";
    private static final String OFFER_RIDES_BUTTON_ID  = "nav_offer_key";
    private static final String PROFILE_BUTTON_ID  = "nav_profile_key";

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement mainPage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + MAIN_PAGE_ID + "\")")));
    }

    private WebElement searchRidesButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + SEARCH_RIDES_BUTTON_ID + "\")")));
    }

    public void tapSearchRides() {searchRidesButton().click();}

    private WebElement listRidesButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + LIST_RIDES_BUTTON_ID + "\")")));
    }

    public void tapListRides() {listRidesButton().click();}

    private WebElement offerRidesButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + OFFER_RIDES_BUTTON_ID + "\")")));
    }

    public void tapOfferRides() {offerRidesButton().click();}

    public boolean isOfferRidesButtonAvailable() {
        try {
            return offerRidesButton().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isOfferRidesButtonAbsent() {
       return driver.findElements(
               AppiumBy.androidUIAutomator(
                       "new UiSelector().resourceId(\"" + OFFER_RIDES_BUTTON_ID + "\")")
              ).isEmpty();
    }

    private WebElement profileButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"" + PROFILE_BUTTON_ID + "\")")));
    }

    public void tapProfile() {profileButton().click();}


    public boolean isDisplayed() {
        try {
            return mainPage().isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}