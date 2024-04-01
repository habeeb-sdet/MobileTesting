package com.epam.listeners;

import com.epam.drivermanager.Driver;
import com.epam.exceptions.InvalidHostException;
import com.epam.utils.reporting.*;
import com.epam.utils.reporting.SystemLogs;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.MalformedURLException;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.startTest(result);
        ExtentReport.startTest(result);
        VideoLog.startRecording(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.endTest(result, true);
        VideoLog.stopRecording(result, true);
        VideoLog.startRecording(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.endTest(result, false);
        ExtentReport.logErrorAndAttachScreenShot(result);
        VideoLog.stopRecording(result, false);
        SystemLogs.captureLogCatLogs(result);
        SystemLogs.captureBugReport();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        try {
            Driver.instantiateDriver();
        } catch (MalformedURLException e) {
            throw new InvalidHostException();
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException();
        }
        ExtentReport.initializeExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        try{
            VideoLog.stopRecording();
            ExtentReport.flushReport();
        }catch (Exception e){
            logger.error("ERROR" + e.getMessage());
        }finally {
            Driver.quitDriver();
        }
    }
}
