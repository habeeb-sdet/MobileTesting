package com.epam.utils.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.epam.drivermanager.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import java.io.File;

import static com.epam.constants.Commons.USER_DIR;


public class ExtentReport {

    private static final Logger logger = LogManager.getLogger(ExtentReport.class);
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void initializeExtentReport(){
        logger.info("Initializing Extent report...");
        String reportFilePath = USER_DIR + "\\reports\\Report.html";
        File existingReportFile = new File(reportFilePath);
        existingReportFile.delete();

        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportFilePath);
        extentReports.attachReporter(extentSparkReporter);

        extentReports.setSystemInfo("Platform", Driver.getType().name());
    }

    public static void startTest(ITestResult result){
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.assignCategory(result.getTestClass().getName());
    }

    public static void log(String log){
        extentTest.info(log);
    }

    public static void log(Markup log){
        extentTest.info(log);
    }

    public static ExtentTest getExtentTest(){
        return ExtentReport.extentTest;
    }

    public static void attachFile(){
    }

    public static void logErrorAndAttachScreenShot(ITestResult result){
        extentTest.fail(result.getThrowable().getMessage());
        TakesScreenshot takesScreenshot = Driver.getDriver();
        String screenShot = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        extentTest.addScreenCaptureFromBase64String(screenShot);
    }

    public static void flushReport(){
        logger.info("Flushing the report");
        extentReports.flush();
    }

}
