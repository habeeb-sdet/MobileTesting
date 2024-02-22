package com.epam.utils.reporting;

import com.epam.drivermanager.Driver;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

public class Logger {

    private final org.slf4j.Logger logger;

    public Logger(Class<?> clazz){
        logger = LoggerFactory.getLogger(clazz);
    }

    public void startTest(ITestResult result){
        logger.info("***** Starting Test : " + result.getMethod().getMethodName() + " from " + result.getTestClass().getName() + " *****");
    }

    public void info(String log){
        logger.info(log);
    }

    public void debug(String log){
        logger.debug(log);
    }

    public void error(String error){
        logger.error(error);
    }

    /*public void logToReport(String log){
        logger.info(log);
        ExtentReport.log(log);
    }*/

    public void endTest(ITestResult result, boolean isPassed){
        String message = "***** Test : " + result.getMethod().getMethodName() + "from " + result.getTestClass().getName();
        if(isPassed){
            logger.info(message + " successfully completed *****");
        }else{
            String error = result.getThrowable().getMessage();
            logger.error(error);
            logger.info("Page Source : " + Driver.getDriver().getPageSource());
            logger.info(message + " failed *****");
        }
    }

}
