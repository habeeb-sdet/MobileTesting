package com.epam.drivermanager.drivercapabilities.android;

import com.epam.constants.AppInfo;
import com.epam.constants.TestProps;
import com.epam.utils.nodemanager.DeviceInfo;
import com.epam.utils.nodemanager.NodeManager;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.time.Duration;

public class LocalAndroidCapabilities implements AndroidDeviceCapabilities {

    public UiAutomator2Options get(){
        DeviceInfo deviceInfo = NodeManager.get().getNodeInfo();

        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setPlatformVersion(TestProps.getOsVersion())
                .setApp(AppInfo.Android.TEST_APP_FILE_PATH)
                .setAppPackage(AppInfo.Android.TEST_APP_PACKAGE)
                .setAppActivity(AppInfo.Android.TEST_APP_ACTIVITY)
                .setNewCommandTimeout(Duration.ofMinutes(2))
                .clearDeviceLogsOnStart()
                .enablePerformanceLogging()
                .printPageSourceOnFindFailure()
                .setSystemPort(deviceInfo.getSystemPort())
                .setMjpegServerPort(deviceInfo.getMjpegServerPort())
                .setMjpegScreenshotUrl(deviceInfo.getMjpegScreenshotUrl());

        return options;
    }
}
