package com.epam.constants;

import io.opentelemetry.api.internal.StringUtils;
import lombok.Data;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.epam.constants.Commons.RESOURCES_DIR;

@Data
public class TestProps {

    @Getter
    private static TestSetUp.Platform testSetUp;
    @Getter
    private static DriverType platform;
    @Getter
    private static String osVersion;
    @Getter
    private static String logLevel;

    static {
        System.out.println("Reading properties file...");
        String file = RESOURCES_DIR + "\\test.properties";
        Properties properties = null;
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Error reading prop file : " + e.getMessage());
            System.exit(-1);
        }


        testSetUp = TestSetUp.Platform.valueOf(getProperty(properties, "testSetup").toUpperCase());
        platform = DriverType.valueOf(getProperty(properties, "platform").toUpperCase());
        osVersion = getProperty(properties, "osVersion");
        logLevel = getProperty(properties, "logLevel");

    }

    /**
     *
     * @param properties Properties object to read the property
     * @param key key to retrieve the data
     * @return property value
     */
    private static String getProperty(Properties properties, String key){
        String value = System.getProperty(key);
        if(StringUtils.isNullOrEmpty(value))
            value = properties.getProperty(key);
        else
            properties.put(key, value);

        return value;
    }




}
