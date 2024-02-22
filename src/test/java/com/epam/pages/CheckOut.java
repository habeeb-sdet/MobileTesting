package com.epam.pages;

import com.epam.utils.locators.AndroidLocator;
import com.epam.utils.locators.IOSLocator;
import com.epam.utils.locators.Locator;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static com.epam.constants.UIMessages.THANK_YOU_CONTENT;
import static com.epam.constants.UIMessages.THANK_YOU_HEADER;
import static com.epam.constants.LocatorType.ACCESSIBILITY_ID;
import static com.epam.constants.LocatorType.XPATH;

public class CheckOut {

    private static Logger logger = LogManager.getLogger(CheckOut.class);

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-First Name")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeTextField[@name='test-First Name']")
    private By firstName;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Last Name")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeTextField[@name='test-Last Name']")
    private By lastName;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Zip/Postal Code")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeTextField[@name='test-Zip/Postal Code']")
    private By zipOrPostalCode;

    @AndroidLocator(type = XPATH, value = "//android.widget.TextView[@text='CONTINUE']")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-CONTINUE']")
    private By continueCheckout;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-FINISH")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-FINISH']")
    private By finishCheckout;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-CHECKOUT: COMPLETE!")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-CHECKOUT: COMPLETE!']")
    private By checkOutCompleteInfo;

    @AndroidLocator(type = XPATH, value = "//android.widget.ScrollView[@content-desc='test-CHECKOUT: COMPLETE!']/android.view.ViewGroup/android.widget.TextView")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[contains(@name, 'THANK YOU FOR YOU ORDER')]/XCUIElementTypeStaticText")
    private By thankYouMessages;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-BACK HOME")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-BACK HOME']")
    private By backHome;

    public CheckOut(){
        Locator.initElements(this);
    }


    public CheckOut fillCheckoutDetails(String firstName, String lastName, String postalCode) {
        String message = String.format("Fill checkout details : fName - %s, lName - %s, postalCode - %s", firstName, lastName, postalCode);
        logger.info(message);
        ExtentReport.log(message);
        UserAction.type(this.firstName, firstName);
        UserAction.type(this.lastName, lastName);
        UserAction.type(this.zipOrPostalCode, postalCode);
        return this;
    }

    public CheckOut continueToCheckout() {
        String message = "Continue to checkout";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.scrollToElementAndTap(this.continueCheckout);
        return this;
    }

    public CheckOut finishCheckout(){
        String message = "Finish checkout";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.scrollToElementAndTap(this.finishCheckout);
        return this;
    }

    public CheckOut verifyThankYou(){
        String message = "Verify thank you message";
        logger.info(message);
        ExtentReport.log(message);
        // Check if getText works for IOS and fix it
        List<WebElement> thankYouMessage = UserAction.getElements(this.thankYouMessages);
        UserAction.verifyText(thankYouMessage, Arrays.asList(THANK_YOU_HEADER, THANK_YOU_CONTENT));
        return this;
    }

    public Products backToHome(){
        String message = "Back to Home";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.click(backHome);
        return new Products();
    }



}
