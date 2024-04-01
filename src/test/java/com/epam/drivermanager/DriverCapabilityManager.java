package com.epam.drivermanager;

import com.epam.constants.TestProps;
import com.epam.drivermanager.drivercapabilities.DriverCapabilities;
import com.epam.drivermanager.drivercapabilities.android.AndroidDeviceCapabilities;
import com.epam.drivermanager.drivercapabilities.android.LocalAndroidCapabilities;
import com.epam.drivermanager.drivercapabilities.android.SauceLabAndroidCapabilities;
import com.epam.drivermanager.drivercapabilities.ios.IosDeviceCapabilities;
import com.epam.drivermanager.drivercapabilities.ios.LocalIosCapabilities;
import com.epam.drivermanager.drivercapabilities.ios.SauceLabIosCapabilities;
import com.epam.exceptions.InvalidDeviceException;

public class DriverCapabilityManager {

    public static DriverCapabilities getDeviceCapability(){
        switch (TestProps.getPlatform()){
            case ANDROID: return getAndroidCapabilities();
            case IOS: return getIOSCapabilities();
        }

        throw new InvalidDeviceException(TestProps.getPlatform().name());
    }

    private static AndroidDeviceCapabilities getAndroidCapabilities(){
        switch (TestProps.getTestSetUp()){
            case SAUCE_LABS: return new SauceLabAndroidCapabilities();
            case GRID:
            case LOCAL_SERVICE:
            default: return new LocalAndroidCapabilities();
        }
    }

    private static IosDeviceCapabilities getIOSCapabilities(){
        switch (TestProps.getTestSetUp()){
            case SAUCE_LABS: return new SauceLabIosCapabilities();
            case GRID:
            case LOCAL_SERVICE:
            default: return new LocalIosCapabilities();
        }
    }
}
