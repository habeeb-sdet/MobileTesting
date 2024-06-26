package com.epam.drivermanager.driver;

import com.epam.constants.DriverType;
import com.epam.drivermanager.drivercapabilities.DriverCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.screenrecording.CanRecordScreen;

import java.net.URL;

import static com.epam.constants.DriverType.IOS;

public class IOSDeviceDriver implements IDriver {

    private IOSDriver iosDriver;

    public IOSDeviceDriver(URL host, XCUITestOptions options){
        iosDriver = new IOSDriver(host, options);
    }

    public IOSDeviceDriver(URL host, DriverCapabilities capabilities){
        iosDriver = new IOSDriver(host, capabilities.get());
    }
    @Override
    public AppiumDriver getDriver() {
        return this.iosDriver;
    }

    @Override
    public SupportsContextSwitching getContextHandler() {
        return this.iosDriver;
    }

    @Override
    public CanRecordScreen getScreenRecorder() {
        return this.iosDriver;
    }

    @Override
    public DriverType type() {
        return IOS;
    }
}
