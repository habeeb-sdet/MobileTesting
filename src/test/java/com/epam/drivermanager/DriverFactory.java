package com.epam.drivermanager;

import com.epam.constants.TestProps;
import com.epam.constants.TestSetUp;
import com.epam.drivermanager.driver.AndroidDeviceDriver;
import com.epam.drivermanager.driver.IDriver;
import com.epam.drivermanager.driver.IOSDeviceDriver;
import com.epam.drivermanager.drivercapabilities.DriverCapabilities;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.NoSuchDriverException;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<AppiumDriverLocalService> appiumDriverLocalServiceThreadLocal = new ThreadLocal<>();
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
            case GRID : url = TestSetUp.Url.getGRID_URL();break;
            case LOCAL_SERVICE:
            default: url = startAppiumServiceAndGetUrl();
        }
        return url;
    }

    private static String startAppiumServiceAndGetUrl(){
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.usingAnyFreePort();

        AppiumDriverLocalService localService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        localService.start();
        appiumDriverLocalServiceThreadLocal.set(localService);
        return localService.getUrl().toString();
    }

}
