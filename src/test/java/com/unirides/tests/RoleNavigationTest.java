package com.unirides.tests;

import com.unirides.base.BaseTest;
import com.unirides.pages.MainPage;
import com.unirides.support.AuthFlow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleNavigationTest extends BaseTest {

    @Test
    public void DriverSeesOfferRidesOption() {
        AuthFlow authFlow = new AuthFlow(driver,config);
        MainPage mainPage = new MainPage(driver);

        authFlow.loginAsDriver();
        assertTrue(mainPage.isOfferRidesButtonAvailable(), "Offer rides button should be displayed when user is a driver");
    }

    @Test
    public void PassengerDoesNotSeeOfferRidesOption() {
        AuthFlow authFlow = new AuthFlow(driver,config);
        MainPage mainPage = new MainPage(driver);

        authFlow.loginAsPassenger();
        assertTrue(mainPage.isOfferRidesButtonAbsent(), "Offer rides button should not be visible when user is a passenger");
    }
}
