package com.epam.pages;

import com.epam.constants.DriverType;
import com.epam.constants.TestProps;
import com.epam.drivermanager.Driver;
import com.epam.utils.locators.AndroidLocator;
import com.epam.utils.locators.IOSLocator;
import com.epam.utils.locators.Locator;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static com.epam.constants.LocatorType.ACCESSIBILITY_ID;
import static com.epam.constants.LocatorType.XPATH;

public class Products {

    private static Logger logger = LogManager.getLogger(Products.class);

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Menu")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-Menu']")
    private By menuIcon;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Cart")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-Cart']")
    private By cartIcon;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Cart drop zone")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='headerContainer']")
    private By testCartDropZone;

    @AndroidLocator(type = XPATH, value = "//android.widget.TextView[@text='PRODUCTS']")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='PRODUCTS']")
    private By productsHeader;

    @AndroidLocator(type = ACCESSIBILITY_ID, value = "test-Modal Selector Button")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-Modal Selector Button']")
    private By filterIcon;

    @AndroidLocator(type = XPATH, value = "//android.widget.TextView[@text='ADD TO CART']")
    @IOSLocator(type = XPATH, value = "//XCUIElementTypeOther[@name='test-ADD TO CART']")
    private By addToCart;

    public By getProductLocator(String productName){
        By path = null;
        if(TestProps.getPlatform() == DriverType.IOS)
            path =  By.xpath("//XCUIElementTypeStaticText[@name='test-Item title' and @label='" + productName + "']");
        else if(TestProps.getPlatform() == DriverType.ANDROID)
            path = By.xpath("//android.widget.TextView[@content-desc='test-Item title' " +
                    "and @text='" + productName + "']");
        return path;
    }

    public Products(){
//        PageFactory.initElements(Driver.getWebDriver(), this);
        Locator.initElements(this);
    }

    public Products validateProductPage(){
        String message = "Validating products page";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.verifyIfElementVisible(menuIcon);
        UserAction.verifyIfElementVisible(cartIcon);
        UserAction.verifyIfElementVisible(testCartDropZone);
        UserAction.verifyIfElementVisible(productsHeader);
        UserAction.verifyIfElementVisible(filterIcon);
        return this;
    }

    public Menu selectMenu(){
        String message = "Select Menu Button";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.click(menuIcon);
        return new Menu();
    }

    public Products sortItems(String sortBy){
        String message = "Sort Items By " + sortBy;
        logger.info(message);
        ExtentReport.log(message);
        String locator = String.format("//android.widget.TextView[@text='%s']", sortBy);
        UserAction.click(this.filterIcon);
        UserAction.click(By.xpath(locator));
        return this;
    }

    public Products selectItem(String itemName){
        String message = "Select Item : " + itemName;
        logger.info(message);
        ExtentReport.log(message);
        By locator = getProductLocator(itemName);
        UserAction.scrollToElementAndTap(locator);
        return this;
    }

    public Products addToCart(){
        String message = "Click on Add To Cart";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.actionMethods(addToCart);
//        UserAction.scrollToElementAndTap(addToCart);
        return this;
    }

    public void validatePrice(){
        String message = "Validate price of the item";
        logger.info(message);
        ExtentReport.log(message);
        By noOfItems = By.xpath("./android.view.ViewGrou/android.widget.TextView]");
        WebElement listOfItems = Driver.getDriver().findElement(addToCart).findElement(noOfItems);
        Assert.assertEquals(listOfItems.getText(), "", "");
    }

    public Cart moveToCartPage(){
        String message = "Move to cart page";
        logger.info(message);
        ExtentReport.log(message);
        UserAction.click(cartIcon);
        return new Cart();
    }

    public Products addItemUsingDragAndDrop(String productName){
        By source = By.xpath("//android.widget.TextView[@content-desc='test-Item title' " +
                "and @text='" + productName + "']/../android.view.ViewGroup[@content-desc='test-Drag Handle']");

        By target =By.xpath("//*[@text='Drag here to add to cart!']");

        UserAction.dragAndDrop(source, target);
        return this;
    }
}
