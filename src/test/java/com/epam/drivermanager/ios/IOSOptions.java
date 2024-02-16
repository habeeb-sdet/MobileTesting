package com.epam.drivermanager.ios;

import com.epam.constants.AppInfo;
import com.epam.drivermanager.DriverOptions;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;

public class IOSOptions implements DriverOptions {

    public XCUITestOptions get(){
        return new XCUITestOptions()
                .setPlatformName("iOS")
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setDeviceName("Iphone 15")

                .setApp(AppInfo.IOS.TEST_APP_FILE_PATH);
    }
}
