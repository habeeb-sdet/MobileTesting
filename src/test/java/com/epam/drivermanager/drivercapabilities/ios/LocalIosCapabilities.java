package com.epam.drivermanager.drivercapabilities.ios;

import com.epam.constants.AppInfo;
import com.epam.constants.TestProps;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;

public class LocalIosCapabilities implements IosDeviceCapabilities{

    public MutableCapabilities get(){
        return new XCUITestOptions()
                .setPlatformName("iOS")
                .setAutomationName("XCuiTest")
                .setPlatformVersion(TestProps.getOsVersion())
                .setApp(AppInfo.IOS.TEST_APP_FILE_PATH)
                .setBundleId(AppInfo.IOS.TEST_APP_PACKAGE);
    }
}
