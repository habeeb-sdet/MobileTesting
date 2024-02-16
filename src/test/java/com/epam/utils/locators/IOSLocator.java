package com.epam.utils.locators;

import com.epam.constants.LocatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IOSLocator {
    String accessibilityId() default "";
    String id() default "";
    String xPath() default "";
    String css() default "";

    LocatorType type() default LocatorType.NONE;

    String value() default "";
}
