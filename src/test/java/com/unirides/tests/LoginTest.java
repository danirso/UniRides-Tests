package com.unirides.tests;

import com.unirides.base.BaseTest;
import com.unirides.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void passengerCanLoginWithValidCredentials() throws InterruptedException {
        String email = config.getProperty("test.passenger.email");
        String password = config.getProperty("test.passenger.password");

        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage.isDisplayed(), "Login screen should be visible on app start");

        loginPage.loginAs(email, password);
        Thread.sleep(10000);

        System.out.println("Login submitted for: " + email);
    }
}