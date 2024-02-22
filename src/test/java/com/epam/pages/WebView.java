package com.epam.pages;

import com.epam.utils.locators.AndroidLocator;
import com.epam.utils.locators.IOSLocator;
import com.epam.utils.locators.Locator;
import com.epam.pages.amazon.Amazon;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import org.openqa.selenium.By;

import static com.epam.constants.LocatorType.ACCESSIBILITY_ID;
import static com.epam.constants.LocatorType.XPATH;

public class WebView extends UserAction {

    private static Logger logger = LogManager.getLogger(WebView.class);

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-enter a https url here...")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeTextField[@name='test-enter a https url here...']")
    public By urlInputBox;

    @AndroidLocator(type = XPATH, value = "//android.widget.TextView[@text='GO TO SITE']")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-GO TO SITE']")
    public By gotoSite;

    public WebView(){
//        PageFactory.initElements(Driver.getWebDriver(), this);
        Locator.initElements(this);
    }

    public WebView enterUrl(String url){
        String message = "Enter url " + url + " in the search box";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.type(this.urlInputBox, url);
        return this;
    }

    public Amazon tapOnGoToSite(){
        String message = "Perform tap to go the web site";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.scrollToElementAndTap(this.gotoSite);
        return new Amazon();
    }
}
