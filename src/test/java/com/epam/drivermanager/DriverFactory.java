package com.epam.drivermanager;

import com.epam.constants.TestProps;
import com.epam.constants.TestSetUp;
import com.epam.drivermanager.driver.AndroidDeviceDriver;
import com.epam.drivermanager.driver.IDriver;
import com.epam.drivermanager.driver.IOSDeviceDriver;
import com.epam.drivermanager.drivercapabilities.DriverCapabilities;
import org.openqa.selenium.remote.NoSuchDriverException;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private DriverFactory(){}

    public static IDriver get() throws MalformedURLException {
        URL url = new URL(getUrl());
        DriverCapabilities capabilities = DriverCapabilityManager.getDeviceCapability();
        IDriver driver = null;
        switch (TestProps.getPlatform()){
            case ANDROID : {
                driver = new AndroidDeviceDriver(url, capabilities);
                break;
            }
            case IOS : {
                driver = new IOSDeviceDriver(url, capabilities);
                break;
            }
            default: throw new NoSuchDriverException("Driver for " + TestProps.getPlatform() +" not found.");
        }

        return driver;
    }

    private static String getUrl() {
        String url = null;
        switch (TestProps.getTestSetUp()){
            case SAUCE_LABS: url = TestSetUp.Url.getSAUCE_LAB_URL();break;
            case LOCAL :
            default: url = TestSetUp.Url.getLOCAL_HOST_URL();
        }
        return url;
    }

}
