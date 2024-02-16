package com.epam.utils.reporting;

import com.epam.drivermanager.Driver;
import com.epam.utils.file.FileUtils;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.testng.ITestResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import static com.epam.constants.Commons.USER_DIR;

public class VideoLog {

    private static final Logger logger = LogManager.getLogger(VideoLog.class);
    private static String videoLogBaseDirPath;

    public static void createVideoLogDir(){
        videoLogBaseDirPath = USER_DIR + "\\logs\\videos";
        FileUtils.createDir(videoLogBaseDirPath);
    }

    public static void startRecording(ITestResult result){
        logger.info("Started Recording for test : " + result.getMethod().getMethodName());
        Driver.getScreenRecorder().startRecordingScreen();
    }

    public static String stopRecording(){
        return Driver.getScreenRecorder().stopRecordingScreen();
    }

    public static void stopRecording(ITestResult result, boolean isSuccess){
        logger.info("Stopped Recording for test : " + result.getMethod().getMethodName());
        String output = stopRecording();
        if(!isSuccess){
            String dir = videoLogBaseDirPath + "\\" + result.getTestClass().getName();
            FileUtils.createDir(dir);
            String fileName = dir + "\\" + result.getMethod().getMethodName() + ".mp4";
            try(FileOutputStream stream = new FileOutputStream(fileName)){
                stream.write(Base64.getDecoder().decode(output));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
