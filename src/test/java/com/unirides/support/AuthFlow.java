package com.unirides.support;

import com.unirides.pages.LoginPage;
import com.unirides.pages.MainPage;
import io.appium.java_client.android.AndroidDriver;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthFlow {

    private final AndroidDriver driver;
    private final Properties config;

    public AuthFlow(AndroidDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
    }

    public void loginAsPassenger() {
        String email = config.getProperty("test.passenger.email");
        String password = config.getProperty("test.passenger.password");
       loginAs(email,password);
    }

    public void loginAsDriver() {
        String email = config.getProperty("test.driver.email");
        String password = config.getProperty("test.driver.password");
        loginAs(email,password);
    }

    private void loginAs(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isDisplayed(), "Login screen should be visible on app start");

        MainPage mainPage = new MainPage(driver);
        loginPage.loginAs(email,password);
        assertTrue(mainPage.isDisplayed(), "Main page should be visible after user login");
    }
}
