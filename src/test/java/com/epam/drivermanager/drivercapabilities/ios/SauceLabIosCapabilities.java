package com.epam.drivermanager.drivercapabilities.ios;

import com.epam.constants.AppInfo;
import com.epam.drivermanager.drivercapabilities.SauceLabInfo;
import org.openqa.selenium.MutableCapabilities;

public class SauceLabIosCapabilities implements IosDeviceCapabilities{

    @Override
    public MutableCapabilities get(){
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("appium:app", "storage:filename=" + AppInfo.IOS.TEST_APP_NAME);  // The filename of the mobile app
        caps.setCapability("appium:deviceName", "iPhone XR");
        caps.setCapability("appium:platformVersion", "16.3");
        caps.setCapability("appium:automationName", "XCUITest");
        caps.setCapability("appium:appPackage", AppInfo.IOS.TEST_APP_PACKAGE);

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", SauceLabInfo.getUsername());
        sauceOptions.setCapability("accessKey", SauceLabInfo.getAccessKey());
        sauceOptions.setCapability("build", "appium-build-1TRP5");
        sauceOptions.setCapability("name", "Sample Sauce Lab ios Test");
        sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
        caps.setCapability("sauce:options", sauceOptions);

        return caps;
    }
}
