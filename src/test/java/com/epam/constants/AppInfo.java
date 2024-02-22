package com.epam.constants;

import lombok.Data;
import lombok.Getter;

import static com.epam.constants.Commons.*;

public class AppInfo {

    private static final String APP_DIR = RESOURCES_DIR + "\\apps\\";

    public static class Android {

        public static final String TEST_APP_NAME = "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
        public static final String TEST_APP_FILE_PATH = APP_DIR + TEST_APP_NAME;
        public static final String TEST_APP_PACKAGE = "com.swaglabsmobileapp";
        public static final String TEST_APP_ACTIVITY = ".MainActivity";
    }

    public static class IOS {
        public static final String TEST_APP_NAME = "iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa";
        public static final String TEST_APP_FILE_PATH = APP_DIR + TEST_APP_NAME;
        public static final String TEST_APP_PACKAGE = "com.saucelabs.mydemoapp.rn";
    }
}
