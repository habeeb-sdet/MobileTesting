package com.epam.utils.poc;

import com.epam.constants.AppInfo;
import com.epam.drivermanager.Driver;
import com.epam.utils.UserAction;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;


public class MobileScenarios {

    public void switchBetweenApps(){
        String settingsAppPackageName="com.android.settings";
        String settingsAppActivityName="com.android.settings.Settings";

        Driver.getDriver().executeScript("mobile: startActivity",
                ImmutableMap.of("intent", settingsAppPackageName + "/" + ".Settings"));

        Driver.getDriver().executeScript("mobile: startActivity",
                ImmutableMap.of("intent", AppInfo.Android.TEST_APP_PACKAGE + "/" + AppInfo.Android.TEST_APP_ACTIVITY));

    }

    public void readNotifications(){
        AndroidDriver driver = (AndroidDriver) Driver.getDriver();
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.runAppInBackground(Duration.ofSeconds(-1));
        driver.openNotifications();
        Driver.getDriver().executeScript("mobile: openNotifications");
        Driver.getDriver().executeScript("mobile: statusBar", ImmutableMap.of("command", "collapse"));
        Driver.getDriver().executeScript("mobile:listSms", ImmutableMap.of("max", 10));

    }


    public void putAppInBackground(){
        Driver.getDriver().executeScript("mobile: backgroundApp", ImmutableMap.of("seconds", -1));
        Driver.getDriver().executeScript("mobile: startActivity",
                ImmutableMap.of("intent", AppInfo.Android.TEST_APP_PACKAGE + "/" + AppInfo.Android.TEST_APP_ACTIVITY));
    }

    public void getPerformanceData(){
        Driver.getDriver().executeScript("mobile: getPerformanceData", ImmutableMap.of("packageName", AppInfo.Android.TEST_APP_PACKAGE, "dataType", "batteryinfo"));
        Driver.getDriver().executeScript("mobile: getPerformanceData", ImmutableMap.of("packageName", AppInfo.Android.TEST_APP_PACKAGE, "dataType", "cpuinfo"));
        Driver.getDriver().executeScript("mobile: getPerformanceData", ImmutableMap.of("packageName", AppInfo.Android.TEST_APP_PACKAGE, "dataType", "memoryinfo"));
        Driver.getDriver().executeScript("mobile: getPerformanceData", ImmutableMap.of("packageName", AppInfo.Android.TEST_APP_PACKAGE, "dataType", "networkinfo"));

        Driver.getDriver().executeScript("mobile: batteryInfo");
        Driver.getDriver().executeScript("mobile: deviceInfo");
    }


    private void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void enableWifi(){
        Driver.getDriver().executeScript("mobile: getConnectivity", ImmutableMap.of("services", "[wifi,data, airplaneMode, bluetooth]"));
        System.out.println("Enable wifi");
        Driver.getDriver().executeScript("mobile: setConnectivity", ImmutableMap.of("wifi", true));
        Driver.getDriver().executeScript("mobile: setConnectivity", ImmutableMap.of("wifi", false));
        Driver.getDriver().executeScript("mobile: setConnectivity", ImmutableMap.of("data", true));
        Driver.getDriver().executeScript("mobile: setConnectivity", ImmutableMap.of("data", false));
        System.out.println("Enable Bluetooth");
    }

    public void unlockThePhone(){
        System.out.println("Unlock the phone");
        AndroidDriver driver = (AndroidDriver)(Driver.getDriver());
        driver.lockDevice();
        System.out.println(driver.isDeviceLocked());
        driver.unlockDevice();

        Driver.getDriver().executeScript("mobile: lock", ImmutableMap.of("seconds", 0));
        Driver.getDriver().executeScript("mobile: unlock", ImmutableMap.of("strategy", "locksettings", "type", "pin", "key", "279580"));
    }


    public void permissions(){
        Driver.getDriver().executeScript("mobile: getPermissions", ImmutableMap.of("appPackage", AppInfo.Android.TEST_APP_PACKAGE));
    }

    public void enableBluetooth(){
        Driver.getDriver().executeScript("mobile: statusBar", ImmutableMap.of("command", "expandSettings"));
        Driver.getDriver().executeScript("mobile: statusBar", ImmutableMap.of("command", "getStatusIcons"));

        By statusBarLocator = By.xpath("//android.widget.LinearLayout[@resource-id='com.android.systemui:id/quick_qs_panel']/android.widget.LinearLayout");
        WebElement statusBar = UserAction.getElement(statusBarLocator);
        Dimension size = statusBar.getSize();
        Point point = statusBar.getLocation();

        int x = point.getX() + size.getWidth()/2;
        int y = point.getY() + size.getHeight()/2;

        Dimension screenDim = Driver.getDriver().manage().window().getSize();

        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence = new Sequence(pointerInput, 0)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(pointerInput, Duration.ofMillis(200)))
                .addAction(pointerInput.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), x, screenDim.getHeight()/2))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.RIGHT.asArg()));
        Driver.getDriver().perform(Collections.singleton(sequence));


        Actions actions = new Actions(Driver.getDriver());
        actions.moveToLocation(x, y).clickAndHold()
                .pause(Duration.ofMillis(200))
                .moveByOffset(x, screenDim.getHeight()/2)
                .release();

        actions.perform();


        By bluetoothLocator = By.xpath("//android.view.ViewGroup[contains(@content-desc, 'Bluetooth']");
        UserAction.click(bluetoothLocator);
    }

    public void changeScreenOrientation(){
        AndroidDriver driver = (AndroidDriver) Driver.getDriver();
        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.rotate(ScreenOrientation.PORTRAIT);
        ((IOSDriver) Driver.getDriver()).rotate(ScreenOrientation.LANDSCAPE);
    }

    /*public void changeScreenSize(){
        AndroidDriver driver = (AndroidDriver) Driver.getDriver();
        driver.
    }*/
}
