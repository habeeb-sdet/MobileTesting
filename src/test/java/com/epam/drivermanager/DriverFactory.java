package com.epam.drivermanager;

import com.epam.constants.Commons;
import com.epam.constants.TestProps;
import com.epam.drivermanager.android.AndroidDeviceDriver;
import com.epam.drivermanager.android.AndroidOptions;
import com.epam.drivermanager.ios.IOSDeviceDriver;
import com.epam.drivermanager.ios.IOSOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.NoSuchDriverException;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private DriverFactory(){}

    public static IDriver get() throws MalformedURLException {
        URL test_infra_url = new URL(TestProps.TEST_INFRA_URL);
        IDriver driver;
        switch (TestProps.PLATFORM){
            case ANDROID : {
                UiAutomator2Options options = new AndroidOptions().get();
                driver = new AndroidDeviceDriver(test_infra_url, options);
                break;
            }

            case IOS : {
                XCUITestOptions options = new IOSOptions().get();
                driver = new IOSDeviceDriver(test_infra_url, options);
                break;
            }

            default: throw new NoSuchDriverException("Driver for " + TestProps.PLATFORM +" not found.");
        }

        return driver;
    }

}
