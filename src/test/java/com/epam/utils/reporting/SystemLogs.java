package com.epam.utils.reporting;

import com.epam.drivermanager.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.epam.constants.Commons.USER_DIR;

public class SystemLogs {

    private static String logDirBasePath;

    public static void createLogCatLogsDir(){
        logDirBasePath = USER_DIR + "\\logs\\android\\";
        try {
            FileUtils.forceMkdir(new File(logDirBasePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void captureLogCatLogs(ITestResult result){
        List<LogEntry> logEntryList = Driver.getDriver().manage().logs().get("logcat").getAll();
        String fileName = logDirBasePath + result.getMethod().getMethodName() + "_logcat.txt";
        try {
            FileUtils.writeLines(new File(fileName), logEntryList, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void captureBugReport(){
        List<LogEntry> logEntryList = Driver.getDriver().manage().logs().get("bugreport").getAll();
        String downloadLoc = "";
        String messageToCheck = "Bug report copied to ";
        for(LogEntry entry : logEntryList){
            String entryMessage = entry.getMessage();
            if(entryMessage.contains(messageToCheck)){
                downloadLoc = entryMessage.substring(messageToCheck.length());
                break;
            }
        }

        try {
            FileUtils.moveFileToDirectory(new File(downloadLoc), new File(logDirBasePath), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
