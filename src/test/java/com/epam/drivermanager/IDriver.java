package com.epam.drivermanager;

import com.epam.constants.DriverType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public interface IDriver {


    DriverType type();

    AppiumDriver getDriver() throws MalformedURLException;

    SupportsContextSwitching getContextHandler();

    CanRecordScreen getScreenRecorder();
}
