package com.epam.utils.poc;

import com.epam.drivermanager.Driver;
import com.epam.pages.amazon.Amazon;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.*;

public class POC extends UserAction{

    /*public Amazon selectItemFromSearchResults(int itemNo){
        String message = "Selecting item from search results";
        ExtentReport.log(message);
        String basePath = "//android.view.View[@resource-id='search']/android.view.View";
        String childPath = "/android.view.View/android.view.View[2]";
        String firstElementPath = null;
        List<WebElement> searchResults = UserAction.getElements(By.xpath(basePath));
        WebElement firstElement = null;
        Map<String, String> attributes = new HashMap<>();
        if(itemNo!=1){
            int index = 1;
            for(WebElement element : searchResults){
                By resultElementIdentifierPath = By.xpath("." + childPath);
                WebElement resultElement;
                try{
                    resultElement = getElementInsideElement(element, resultElementIdentifierPath, Duration.ofSeconds(5));
                    String contentDesc = resultElement.getAttribute("content-desc");
                    firstElementPath = basePath + childPath + "[@content-desc=" + contentDesc + "]/../..";
                    attributes.put("content-desc", contentDesc);
                }catch (Exception e){
                    System.out.println("Checking next element to find if it is the search result");
                }
                index++;
            }
        }else{
            tap(By.xpath(basePath + "[" + 1 + "]" + childPath));
        }




//        scrollToElement(resultElementLocator);

        return this;
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

    public WebElement getElementInsideElement(WebElement baseElement, By childElementLocator, Duration waitTimeInSec){
        return waitForNestedElementToBePresent(baseElement, childElementLocator, waitTimeInSec);
    }

    public static WebElement scrollToElement(String firstElementPath, Map<String, String> attributes, String childPath, int index){
        WebElement element = getElement(By.xpath(firstElementPath + childPath));
        if(element.isEnabled()){
            return element;
        }

        Dimension windowSize = Driver.getDriver().manage().window().getSize();
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "Finger 1");
        int startX = windowSize.getWidth()/2;
        int startY = windowSize.getHeight()/2;


        Sequence sequence = new Sequence(finger1, 0)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(400), PointerInput.Origin.viewport(), startX, startY/4))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Driver.getDriver().perform(Collections.singleton(sequence));

        long startTime = System.currentTimeMillis();
        for(int i=0; i<index; i++){

        }
        while(System.currentTimeMillis() - startTime <= (2*60000)){




            element = getElement(By.xpath(firstElementPath + childPath));
            if(!attributes.get("content-desc").equals(element.getAttribute("content-desc"))){
                if(element.isEnabled()){
                    return element;
                }
            }

        }

        throw new NoSuchElementException("Element with locator " + locator + " not found");
    }*/





}
