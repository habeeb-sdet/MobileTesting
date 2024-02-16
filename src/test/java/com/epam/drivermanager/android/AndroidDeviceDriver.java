package com.epam.drivermanager.android;

import com.epam.constants.DriverType;
import com.epam.drivermanager.IDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.constants.DriverType.ANDROID;

public class AndroidDeviceDriver implements IDriver {

    private AndroidDriver androidDriver;

    public AndroidDeviceDriver(URL url, UiAutomator2Options options) {
        androidDriver = new AndroidDriver(url, options);
    }

    @Override
    public AppiumDriver getDriver() throws MalformedURLException {
        return this.androidDriver;
    }

    @Override
    public SupportsContextSwitching getContextHandler() {
        return this.androidDriver;
    }

    @Override
    public CanRecordScreen getScreenRecorder() {
        return this.androidDriver;
    }

    @Override
    public DriverType type() {
        return ANDROID;
    }

}
