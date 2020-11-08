package mobileprism.components;

import mobileprism.screens.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.*;

public abstract class PrismTest {
    public AndroidDriver<MobileElement> driver;
    public String platform;

    void isAppiumRunning() throws UnknownHostException, IOException {
        try {
            (new Socket("localhost", 4723)).close();
        } catch(SocketException e) {
            throw new RuntimeException("could not detect Appium running on port 4732");
        }
    }

    String retrieveProp(String p) throws IllegalArgumentException {
        if ( System.getProperty(p) == null ) {
            throw new IllegalArgumentException("please define property: " + p);
        } else {
            return System.getProperty(p);
        }
    }

    void startDriver() throws MalformedURLException, IllegalArgumentException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", retrieveProp("platform"));
        capabilities.setCapability("deviceName", retrieveProp("deviceName"));
        capabilities.setCapability("platformVersion", retrieveProp("platformVersion"));
        capabilities.setCapability("app", retrieveProp("app"));
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @BeforeTest()
    public void initialize() throws MalformedURLException, UnknownHostException, IOException, IllegalArgumentException {
        isAppiumRunning();
        startDriver();
    }
}
