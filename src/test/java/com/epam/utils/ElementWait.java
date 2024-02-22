package com.epam.utils;

import com.epam.drivermanager.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.epam.constants.TimeOuts.ELEMENT_VISIBILITY_TIME_OUT;

public class ElementWait {

    public static WebElement waitForElementToBeVisibleAndGet(By locator){
        return waitForElementToBeVisibleAndGet(locator, ELEMENT_VISIBILITY_TIME_OUT);
    }

    public static WebElement waitForElementToBeVisibleAndGet(By locator, Duration waitTimeInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSec);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementToBeVisibleAndGet(WebElement element, Duration waitTimeInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> waitForElementListToBeVisibleAndGet(By locator){
        return waitForElementListToBeVisibleAndGet(locator, ELEMENT_VISIBILITY_TIME_OUT);
    }

    public static List<WebElement> waitForElementListToBeVisibleAndGet(By locator, Duration waitTimeInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSec);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static WebElement waitForNestedElementToBePresent(WebElement parentElement, By childLocator, Duration waitTimeInSec){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), waitTimeInSec);
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, childLocator));
    }
}
