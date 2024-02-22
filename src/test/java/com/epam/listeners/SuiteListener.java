package com.epam.listeners;

import com.epam.utils.reporting.SystemLogs;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import com.epam.utils.reporting.VideoLog;
import org.testng.ISuite;
import org.testng.ISuiteListener;


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
        logger.info("\n\t\t\t===== Execution Ended =====\n");
    }
}
