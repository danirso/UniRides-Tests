package com.unirides.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected AndroidDriver driver;
    protected Properties config;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        config = loadConfig();

        String appPath = config.getProperty("app.path");
        String deviceName = config.getProperty("device.name", "emulator-5554");
        String serverUrl = config.getProperty("appium.server.url", "http://127.0.0.1:4723");

        if (appPath == null || appPath.isBlank()) {
            throw new IllegalStateException(
                    "app.path is not set. Create config.properties in the project root " +
                            "(based on config.properties.example) or set the APP_PATH environment variable."
            );
        }

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(deviceName)
                .setApp(appPath)
                .setAutomationName("UiAutomator2")
                .setNewCommandTimeout(Duration.ofSeconds(120))
                .setEnforceAppInstall(true);

        driver = new AndroidDriver(new URL(serverUrl), options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private Properties loadConfig() {
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println("config.properties not found, falling back to environment variables.");
        }

        overrideIfPresent(props, "app.path", "APP_PATH");
        overrideIfPresent(props, "device.name", "DEVICE_NAME");
        overrideIfPresent(props, "appium.server.url", "APPIUM_SERVER_URL");

        return props;
    }

    private void overrideIfPresent(Properties props, String key, String envVar) {
        String value = System.getenv(envVar);
        if (value != null && !value.isBlank()) {
            props.setProperty(key, value);
        }
    }
}