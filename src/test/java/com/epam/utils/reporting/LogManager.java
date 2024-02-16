package com.epam.utils.reporting;

import ch.qos.logback.classic.Level;
import com.epam.constants.TestProps;

public class LogManager {

    public static Logger getLogger(Class<?> clazz){
        return new Logger(clazz);
    }

    public static Level getLogLevel(){
        String logLevel = TestProps.LOG_LEVEL.toUpperCase();
        Level level = null;
        switch (logLevel){
            case "INFO" : return Level.INFO;
            case "DEBUG" : return Level.DEBUG;
        }

        return Level.INFO;
    }


}
