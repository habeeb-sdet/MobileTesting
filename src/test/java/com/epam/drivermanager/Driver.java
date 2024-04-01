package com.epam.drivermanager;

import com.epam.constants.TestProps;
import com.epam.drivermanager.driver.IDriver;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import com.epam.constants.DriverType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.appium.java_client.screenrecording.CanRecordScreen;

import java.net.MalformedURLException;
import java.util.Objects;

public class Driver {

    private static final Logger logger = LogManager.getLogger(Driver.class);
    private static final ThreadLocal<IDriver> IDRIVER_THREAD_LOCAL = new ThreadLocal<>();
    public static final ThreadLocal<AppiumDriver> APPIUM_DRIVER_THREAD_LOCAL = new ThreadLocal<>();


    public static void instantiateDriver() throws MalformedURLException {
        IDriver driver = DriverFactory.get();
        IDRIVER_THREAD_LOCAL.set(driver);
        APPIUM_DRIVER_THREAD_LOCAL.set(driver.getDriver());
    }

    public static AppiumDriver getDriver(){
        return APPIUM_DRIVER_THREAD_LOCAL.get();
    }

    public static SupportsContextSwitching getContextHandler(){
            return IDRIVER_THREAD_LOCAL.get().getContextHandler();
    }

    public static CanRecordScreen getScreenRecorder(){
            return IDRIVER_THREAD_LOCAL.get().getScreenRecorder();
    }

    public static void quitDriver(){
        if(Objects.nonNull(APPIUM_DRIVER_THREAD_LOCAL.get())){
            logger.info("Quitting driver");
            APPIUM_DRIVER_THREAD_LOCAL.get().quit();
            logger.info("Removed driver from thread local variable");
            APPIUM_DRIVER_THREAD_LOCAL.remove();
        }else{
            logger.info("Web Driver object is null and it cannot be quit");
        }
    }

    public static DriverType getType(){
        return TestProps.getPlatform();
    }

}
