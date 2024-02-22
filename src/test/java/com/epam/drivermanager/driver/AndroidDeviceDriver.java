package com.epam.drivermanager.driver;

import com.epam.constants.DriverType;
import com.epam.drivermanager.drivercapabilities.DriverCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.screenrecording.CanRecordScreen;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.constants.DriverType.ANDROID;

public class AndroidDeviceDriver implements IDriver {

    private AndroidDriver androidDriver;

    public AndroidDeviceDriver(URL url, DriverCapabilities capabilities) {
        androidDriver = new AndroidDriver(url, capabilities.get());
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
