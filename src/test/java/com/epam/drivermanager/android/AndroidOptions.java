package com.epam.drivermanager.android;

import com.epam.constants.AppInfo;
import com.epam.constants.TestProps;
import com.epam.drivermanager.DriverOptions;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.time.Duration;


public class AndroidOptions implements DriverOptions {

    private static Logger logger = LogManager.getLogger(AndroidOptions.class);

    @Override
    public UiAutomator2Options get(){
        logger.info("Constructing Driver capabilities...");
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setPlatformVersion(TestProps.OS_VERSION)
                .setApp(AppInfo.Android.TEST_APP_FILE_PATH)
                .setAppPackage(AppInfo.Android.TEST_APP_PACKAGE)
                .setAppActivity(AppInfo.Android.TEST_APP_ACTIVITY)
                .setNewCommandTimeout(Duration.ofMinutes(2))
                .clearDeviceLogsOnStart()
                .enablePerformanceLogging()
                .printPageSourceOnFindFailure()
                .setSystemPort(8110)
                .setMjpegServerPort(9100)
                .setMjpegScreenshotUrl("http://localhost:9100");

        logger.info("Driver Options : " + options);

        return options;
    }

}
