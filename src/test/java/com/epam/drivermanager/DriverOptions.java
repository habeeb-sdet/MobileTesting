package com.epam.drivermanager;

import io.appium.java_client.remote.options.BaseOptions;

public interface DriverOptions {

//    String OS_TYPE = System.getProperty("osType");
    BaseOptions<?> get();
}
