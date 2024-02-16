package com.epam.listeners;

import com.epam.utils.reporting.SystemLogs;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import com.epam.utils.reporting.VideoLog;
import io.appium.java_client.AppiumDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import static com.epam.drivermanager.Driver.APPIUM_DRIVER_THREAD_LOCAL;

public class SuiteListener implements ISuiteListener {

    private static final Logger logger = LogManager.getLogger(ISuiteListener.class);

    @Override
    public void onStart(ISuite suite) {
        logger.info("\n\t\t\t===== Starting a new suite execution =====\n");
        VideoLog.createVideoLogDir();
        SystemLogs.createLogCatLogsDir();
    }

    @Override
    public void onFinish(ISuite suite) {
//        APPIUM_DRIVER_THREAD_LOCAL.

        logger.info("\n\t\t\t===== Execution Ended =====\n");
    }
}
