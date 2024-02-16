package com.epam.utils;

import com.epam.drivermanager.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static com.epam.constants.TimeOuts.ELEMENT_VISIBILITY_TIME_OUT;

public class UserAction extends ElementWait{

    public static WebElement getElement(By locator){
        return waitForElementToBeVisibleAndGet(locator);
    }

    public static List<WebElement> getElements(By locator){
        return waitForElementListToBeVisibleAndGet(locator);
    }

    public static void type(By locator, String input){
        getElement(locator).sendKeys(input);
    }

    public static void type(WebElement element, String input){
        element.sendKeys(input);
    }

    public static void click(By locator){
        getElement(locator).click();
    }

    public static void click(WebElement element){
        element.click();
    }

    public static void verifyIfElementVisible(By locator){
        verifyIfElementVisible(getElement(locator));
    }

    public static void verifyIfElementVisible(WebElement element){
        Assert.assertTrue(element.isDisplayed());
    }

    public static boolean isElementVisible(By locator){
        return isElementVisible(locator, ELEMENT_VISIBILITY_TIME_OUT);
    }

    public static boolean isElementVisible(By locator, Duration waitTimeInSec){
        try{
            waitForElementToBeVisibleAndGet(locator, waitTimeInSec);
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
        return true;
    }

    public  static boolean isElementVisible(WebElement element, Duration waitTimeInSec){
        try{
            waitForElementToBeVisibleAndGet(element, ELEMENT_VISIBILITY_TIME_OUT);
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
        return true;
    }

    public  static boolean isElementVisible(WebElement element){
        try{
            waitForElementToBeVisibleAndGet(element, ELEMENT_VISIBILITY_TIME_OUT);
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
        return true;
    }

    public static String getText(By locator){
        return getText(getElement(locator));
    }

    public static String getText(WebElement element){
        return element.getText();
    }

    public static void scrollToElement(By locator){
        Dimension windowSize = Driver.getDriver().manage().window().getSize();
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger 1");
        int startX = windowSize.getWidth()/2;
        int startY = windowSize.getHeight()/2;

        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime <= 1000000){
            Sequence sequence = new Sequence(finger1, 0)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, Duration.ofMillis(200)))
                    .addAction(finger1.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), startX, startY/4))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            Driver.getDriver().perform(Collections.singleton(sequence));

            if(isElementVisible(locator,Duration.ofSeconds(5))){
                return;
            }
        }

        throw new NoSuchElementException("Element with locator " + locator + " not found");
    }

    public static void checkAndScrollToElement(By locator){
        if(isElementVisible(locator, Duration.ofSeconds(10))){
            return;
        }

        scrollToElement(locator);
    }

    public static void tap(By locator){
        WebElement element = getElement(locator);

        Point elementLocation = element.getLocation();
        Dimension dimension = element.getSize();
        int x = elementLocation.getX() + dimension.getWidth()/2;
        int y = elementLocation.getY() + dimension.getHeight()/2;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger 1");
        Sequence sequence = new Sequence(finger1, 0)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Collections.singleton(sequence));
    }

    public static void scrollToElementAndTap(By locator){
        checkAndScrollToElement(locator);
        tap(locator);
    }

    public static void verifyText(By element, String expectedText){
        Assert.assertEquals(getText(element), expectedText);
    }

    public static void verifyText(List<WebElement> elements, List<String> expectedText){
        SoftAssert softAssert = new SoftAssert();
        for(int i=0; i<elements.size(); i++){
            softAssert.assertEquals(elements.get(i).getText(), expectedText.get(i));
        }
        softAssert.assertAll();
    }

    public static void switchContext(By locator, boolean isNativeContext){
        if(isNativeContext){
            Driver.getContextHandler().context("NATIVE_APP");
            return;
        }

        String currentContext = Driver.getContextHandler().getContext();
        Set<String> contexts = Driver.getContextHandler().getContextHandles();
        for(String context: contexts){
            if(!context.equals("NATIVE_APP") && !context.equals(currentContext)){
                Driver.getContextHandler().context(context);
                if(isElementVisible(locator, Duration.ofSeconds(5))){
                    return;
                }
            }
        }

        Assert.fail("Context to find element " + locator + " is not found");
    }

    public static void clearInputBox(By locator){
        getElement(locator).clear();
    }

    public static void clearInputBox(WebElement element){
        element.clear();
    }



    public static void scrollToElement(WebElement element){
        Dimension windowSize = Driver.getDriver().manage().window().getSize();
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger 1");
        int startX = windowSize.getWidth()/2;
        int startY = windowSize.getHeight()/2;

        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime <= 1000000){
            Sequence sequence = new Sequence(finger1, 0)
                    .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                    .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger1, Duration.ofMillis(200)))
                    .addAction(finger1.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), startX, startY/4))
                    .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            Driver.getDriver().perform(Collections.singleton(sequence));

            if(isElementVisible(element,Duration.ofSeconds(5))){
                return;
            }
        }

        throw new NoSuchElementException(element + " not found");
    }

    public static void checkAndScrollToElement(WebElement element){
        if(isElementVisible(element, Duration.ofSeconds(10))){
            return;
        }

        scrollToElement(element);
    }

    public static void tap(WebElement element){
        Point elementLocation = element.getLocation();
        Dimension dimension = element.getSize();
        int x = elementLocation.getX() + dimension.getWidth()/2;
        int y = elementLocation.getY() + dimension.getHeight()/2;

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger 1");
        Sequence sequence = new Sequence(finger1, 0)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Collections.singleton(sequence));
    }

    public static void scrollToElementAndTap(WebElement element){
        checkAndScrollToElement(element);
        tap(element);
    }
}