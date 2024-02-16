package com.epam.utils.locators;

import com.epam.drivermanager.Driver;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import com.epam.constants.DriverType;
import com.epam.constants.LocatorType;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;

import java.lang.reflect.Field;

public class Locator {

    private static Logger logger = LogManager.getLogger(Locator.class);

    public static void initElements(Object obj) {
        logger.debug("Initializing locators from " + obj.getClass());
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.getType() == By.class){
                try {
                    field.setAccessible(true);
                    field.set(obj, getLocator(field));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static By getLocator(Field field){
        By locator = null;
        if(Driver.getType() == DriverType.ANDROID){
            if(field.isAnnotationPresent(AndroidLocator.class)){
                AndroidLocator androidLocator = field.getAnnotation(AndroidLocator.class);
                locator = getLocator(androidLocator.type(), androidLocator.value());
            }
        }else if(Driver.getType() == DriverType.IOS) {
            if(field.isAnnotationPresent(IOSLocator.class)) {
                IOSLocator iosLocator = field.getAnnotation(IOSLocator.class);
                locator = getLocator(iosLocator.type(), iosLocator.value());
            }
        }

        return locator;
    }

    private static By getLocator(LocatorType type, String value){
        switch (type){
            case ACCESSIBILITY_ID: return AppiumBy.accessibilityId(value);
            case ID: return By.id(value);
            case CSS: return By.cssSelector(value);
            case XPATH: return By.xpath(value);
        }
        throw new InvalidSelectorException("Invalid locator type passed");
    }
}

