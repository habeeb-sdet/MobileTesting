package com.epam.pages;

import com.epam.utils.locators.AndroidLocator;
import com.epam.utils.locators.Locator;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.epam.constants.LocatorType.ACCESSIBILITY_ID;

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
    private By checkOut;

    private String itemName = "//android.widget.TextView[@text='%s']";
    private String itemPrice = "/../following-sibling::android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView";

    public Cart(){
        Locator.initElements(this);
    }

    private By getItemNameLocator(String itemName){
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
    }


    public Cart validateItemDetails(String itemName, String expItemPrice){
        By itemNameLocator = getItemNameLocator(itemName);
        By itemPriceLocator = getItemPriceLocator(itemName);
        String actItemPrice = UserAction.getElement(itemPriceLocator).getText();
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
