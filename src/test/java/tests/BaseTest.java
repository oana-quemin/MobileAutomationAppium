package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    static AndroidDriver driver;
    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeEach
    public void setUp() throws MalformedURLException {
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.fleetio.go_app");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.fleetio.go_app.features.initial.InitialActivity");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "0A181JECB07431");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //push file to phone
        URL url = this.getClass().getResource("/" + Constants.FILENAME_TO_UPLOAD);
        try {
            assert url != null;
            driver.pushFile("/sdcard/download/" + Constants.FILENAME_TO_UPLOAD, new File(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void quitDriver() {
        driver.quit();
    }
}
