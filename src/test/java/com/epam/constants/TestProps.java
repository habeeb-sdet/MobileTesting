package com.epam.constants;

import io.opentelemetry.api.internal.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static com.epam.constants.Commons.RESOURCES_DIR;

public class TestProps {

    public static String TEST_INFRA_URL;
    public static DriverType PLATFORM;
    public static String OS_VERSION;
    public static String LOG_LEVEL;

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


        TEST_INFRA_URL = getProperty(properties, "testInfraUrl");
        PLATFORM = DriverType.valueOf(getProperty(properties, "platform").toUpperCase());
        OS_VERSION = getProperty(properties, "osVersion");
        LOG_LEVEL = getProperty(properties, "logLevel");



        /*try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            properties.store(fileOutputStream, "Updating properties file with user defined values");
        } catch (IOException e) {
            System.err.println("Error while updating prop file : " + e.getMessage());
            System.exit(-1);
        }*/
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
