# UniRides — Appium mobile tests

Android test automation for the [UniRides Flutter app](https://github.com/danirso/tcc_caronas_frontend), developed as an undergraduate final project and connected to a [FastAPI backend](https://github.com/danirso/tcc-caronas-backend). This repository is a QA Automation/SDET learning and portfolio project. The current suite runs locally with Java 21, JUnit 5, Appium, and the UiAutomator2 driver.

## Test coverage

| Test | What it verifies |
| --- | --- |
| `SmokeTest` | Opens an Appium session with the APK on the emulator. |
| `LoginTest` | Checks passenger login and the error message for an incorrect password. |
| `LogoutTest` | Checks that logging out from the profile returns to the login screen. |
| `RoleNavigationTest` | Checks that the offer rides option appears for drivers and is absent for passengers. |

There are **6 test cases** in total. The authentication and navigation tests require two registered and activated local accounts. `SmokeTest` does not require the backend.

## How the automation works

The Java tests send HTTP commands to the Appium server. The UiAutomator2 driver performs actions on Android and returns the results. The APK is installed on the emulator when each session starts, so the tests use the version specified by `app.path`.

```text
JUnit test → Appium Server → UiAutomator2 → Android emulator → Flutter app
                                                             ↕
                                                    local FastAPI backend
```

- `src/test/java/com/unirides/base/BaseTest.java` loads configuration, opens a session before each test, and closes it afterward.
- `src/test/java/com/unirides/pages/` contains Page Objects that group screen selectors and actions.
- `src/test/java/com/unirides/support/AuthFlow.java` reuses login as a precondition for other tests.
- `src/test/java/com/unirides/tests/` contains the test scenarios and JUnit assertions.

## Prerequisites

- JDK 21 and Maven.
- Android SDK, `adb`, and a configured Android emulator.
- Appium Server with the UiAutomator2 driver installed.
- Flutter and the [frontend dependencies](https://github.com/danirso/tcc_caronas_frontend#run-with-the-local-backend) to build a debug APK.
- For all tests except the smoke test: the [local backend](https://github.com/danirso/tcc-caronas-backend#run-locally), with Python and Docker Compose.

Check your installations:

```bash
java -version
mvn -version
adb devices
appium --version
appium driver list --installed
```

If UiAutomator2 is not installed yet, run `appium driver install uiautomator2`.

## Run locally

### 1. Start the backend and prepare test accounts

Follow the backend repository's setup instructions and run this from that repository:

```bash
./scripts/run-local.sh
```

The API runs at `http://127.0.0.1:8000`, and the local Mailpit inbox is at `http://127.0.0.1:8025`. Keep the process running. In Swagger UI at `http://127.0.0.1:8000/docs`, register a passenger account and another account with the driver option enabled. Activate both using the links in Mailpit. Add these accounts to `config.properties` below. The local backend accepts `example.com` accounts for testing.

### 2. Build the debug APK

In the Flutter repository, install dependencies as described in its README and run:

```bash
flutter build apk --debug \
  --dart-define=API_URL=http://10.0.2.2:8000 \
  --dart-define=ALLOW_LOCAL_TEST_EMAILS=true
```

The generated APK is at `build/app/outputs/flutter-apk/app-debug.apk`. If you use address or map features, also add `--dart-define=GOOGLE_MAPS_KEY=<your-key>`. The current tests do not exercise those features.

The Android emulator uses `10.0.2.2` to reach the host computer's `127.0.0.1`. Without this `API_URL`, the app would try to connect to the emulator itself.

### 3. Configure this repository

From the root of this repository, copy the example file and fill in your local values:

```bash
cp config.properties.example config.properties
```

Set `app.path` to the APK's **absolute path**, `device.name` to the device shown by `adb devices`, `appium.server.url` to the Appium server URL, and the credentials for both test accounts. Git ignores `config.properties` because it contains machine-specific values and passwords.

### 4. Start the emulator and Appium

Start the AVD through Android Studio or the terminal. Check that `adb devices` shows its status as `device`, then start Appium in another terminal:

```bash
appium
```

The tests use `http://127.0.0.1:4723` by default.

### 5. Run the tests

From the root of this repository:

```bash
mvn test
```

To check only the Appium session and APK installation, without starting the backend:

```bash
mvn -Dtest=SmokeTest test
```

Results are written to `target/surefire-reports/`. Run the tests locally and sequentially, with the emulator and required services available.

## Current status and next steps

The local suite covers session startup, authentication, logout, and navigation differences between user roles. Ride workflows, CI execution, and Allure reports are not implemented in this repository yet. Future scenarios will be selected based on business value, test data, and reliable assertions.
