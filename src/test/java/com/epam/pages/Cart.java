package com.epam.pages;

import com.epam.constants.TestProps;
import com.epam.utils.locators.AndroidLocator;
import com.epam.utils.locators.IOSLocator;
import com.epam.utils.locators.Locator;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.epam.constants.DriverType.ANDROID;
import static com.epam.constants.DriverType.IOS;
import static com.epam.constants.LocatorType.ACCESSIBILITY_ID;
import static com.epam.constants.LocatorType.XPATH;

public class Cart extends Page {

    private static Logger logger = LogManager.getLogger(Cart.class);

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Cart Content")
    private By cartContents;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Item")
    private By cartItems;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Amount")
    private By itemQuantity;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Description")
    private By itemDetail;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-CHECKOUT")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-CHECKOUT']")
    private By checkOut;

//    private String itemName = ;
//    private String itemPrice = ;

    public Cart(){
        Locator.initElements(this);
    }

    /*private By getItemNameLocator(String itemName){
        String path = String.format(this.itemName, itemName);
        String message = "Item Name Locator : " + itemName;
        logger.info(message);
        ExtentReport.log(message);
        return By.xpath(path);
    }

    private By getItemPriceLocator(String itemName){
        String itemNamePath = String.format(this.itemName, itemName);
        String message = "Item Price Locator : " + itemName;
        logger.info(message);
        ExtentReport.log(message);
        return By.xpath(itemNamePath + itemPrice);
    }*/

    private String getItemNameLocator(String itemName){
        String path = null;
        if(TestProps.getPlatform() == IOS){
            path = "//XCUIElementTypeStaticText[@name='" + itemName + "']";
        }else if(TestProps.getPlatform() == ANDROID){
            path = "//android.widget.TextView[@text='" + itemName + "']";
        }

        return path;
    }

    private String getItemPriceLocator(String itemName){
        String itemNameLocator = getItemNameLocator(itemName);
        String path = null;
        if(TestProps.getPlatform() == IOS){
            path = itemNameLocator + "/../following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText";
        }else if(TestProps.getPlatform() == ANDROID){
            path = itemNameLocator + "/../following-sibling::android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView";
        }

        return path;
    }


    public Cart validateItemDetails(String itemName, String expItemPrice){
        By itemNameLocator = By.xpath(getItemNameLocator(itemName));
        By itemPriceLocator = By.xpath(getItemPriceLocator(itemName));
        String actItemPrice = null;
        if(TestProps.getPlatform() == IOS){
            actItemPrice = UserAction.getElement(itemPriceLocator).getAttribute("value");
        }else if(TestProps.getPlatform() == ANDROID){
            actItemPrice = UserAction.getElement(itemPriceLocator).getText();
        }
        String message = "Validating if " + itemName + "is visible";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.verifyIfElementVisible(itemNameLocator);
        message = "Comparing act price of the item with the exp price";
        logger.info(message);
        ExtentReport.log(message);
        Assert.assertEquals(actItemPrice, expItemPrice);
        return this;
    }

    public CheckOut checkOut(){
        String message = "Performing check-out";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.click(this.checkOut);
        return new CheckOut();
    }
}
