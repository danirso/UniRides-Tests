package com.unirides.tests;

import com.unirides.base.BaseTest;
import com.unirides.pages.LoginPage;
import com.unirides.pages.MainPage;
import com.unirides.pages.ProfilePage;
import com.unirides.support.AuthFlow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {

    @BeforeEach
    public void loginBeforeTests() {
        AuthFlow authFlow = new AuthFlow(driver, config);
        authFlow.loginAsPassenger();
    }

    @Test
    public void userCanLogout() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.tapProfile();
        assertTrue(profilePage.isDisplayed(),"Profile page should be displayed when button is clicked");

        profilePage.tapLogoutButton();
        assertTrue(loginPage.isDisplayed(), "Login page should be displayed after user logs out");
    }
}
