package com.unirides.tests;

import com.unirides.base.BaseTest;
import com.unirides.pages.LoginPage;
import com.unirides.pages.MainPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginWithValidCredentials(){
        String email = config.getProperty("test.passenger.email");
        String password = config.getProperty("test.passenger.password");

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isDisplayed(), "Login screen should be visible on app start");

        MainPage mainPage = new MainPage(driver);
        loginPage.loginAs(email, password);
        assertTrue(mainPage.isDisplayed(), "Main page should be visible after login is done");

    }

    @Test
    public void userCantLoginWithWrongPassword() {
        String email = config.getProperty("test.passenger.email");
        String password = "wrongPassword";

        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.isDisplayed(), "Login screen should be visible on app start");

        loginPage.loginAs(email, password);
        assertTrue(loginPage.isErrorDisplayed("Login ou senha incorretos."));

    }
}