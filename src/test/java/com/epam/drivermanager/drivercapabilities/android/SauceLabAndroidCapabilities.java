package com.epam.drivermanager.drivercapabilities.android;

import com.epam.constants.AppInfo;
import com.epam.drivermanager.drivercapabilities.SauceLabInfo;
import org.openqa.selenium.MutableCapabilities;

public class SauceLabAndroidCapabilities implements AndroidDeviceCapabilities{

    public MutableCapabilities get(){
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", SauceLabInfo.getUsername());
        sauceOptions.setCapability("accessKey", SauceLabInfo.getAccessKey());
        sauceOptions.setCapability("build", "appium-build-1TRP5");
        sauceOptions.setCapability("name", "Sample sauce lab android test");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:app", "storage:filename=" + AppInfo.Android.TEST_APP_NAME);  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage", AppInfo.Android.TEST_APP_PACKAGE);
        caps.setCapability("appium:appActivity", AppInfo.Android.TEST_APP_ACTIVITY);
        caps.setCapability("sauce:options", sauceOptions);

        return caps;
    }
}
