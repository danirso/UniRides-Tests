package com.unirides.tests;

import com.unirides.base.BaseTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmokeTest extends BaseTest {

    @Test
    public void sessionOpensSuccessfully() {
        assertNotNull(driver, "Driver should have been initialized by setUp()");
        assertNotNull(driver.getSessionId(), "Appium session should have a valid session id");

        System.out.println("Session ID: " + driver.getSessionId());
        System.out.println("Current package: " + driver.getCurrentPackage());
    }
}