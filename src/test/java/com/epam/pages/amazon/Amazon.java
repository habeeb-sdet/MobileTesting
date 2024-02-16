package com.epam.pages.amazon;

import com.epam.drivermanager.Driver;
import com.epam.utils.locators.Locator;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.epam.constants.TimeOuts.ELEMENT_VISIBILITY_TIME_OUT;

public class Amazon extends UserAction {

    private static Logger logger = LogManager.getLogger(Amazon.class);

    /*@AndroidLocator(type = XPATH, value = "//android.widget.EditText[@resource-id='nav-search-keywords']")
    private By searchBox;

    @AndroidLocator(type = XPATH, value = "//android.widget.Button[@text='Go']")
    private By searchBtn;

    @AndroidLocator(type = XPATH, value = "[data-component-type='s-search-result']")
    private By searchResults;

    @AndroidLocator(type = XPATH, value = "//android.widget.Button[@resource-id='add-to-cart-button']")
    private By addToCart;

    @AndroidLocator(type = XPATH, value = "//android.widget.TextView[@text='Added to cart']")
    private By addToCartSuccessMessage;

    @AndroidLocator(type = XPATH, value = "//android.view.View[@content-desc='Tap here to login to your account']")
    private By tapHereToLogIn;

    @AndroidLocator(type = XPATH, value = "//android.widget.TextView[@text='✕']")
    private By closeX;*/


    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='nav-search-keywords']")
    private WebElement searchBox;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Go']")
    private WebElement searchBtn;

    @AndroidFindBy(xpath = "[data-component-type='s-search-result']")
    private WebElement searchResults;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='add-to-cart-button']")
    private WebElement addToCart;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Added to cart']")
    private WebElement addToCartSuccessMessage;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Tap here to login to your account']")
    private WebElement tapHereToLogIn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='✕']")
    private WebElement closeX;

    public Amazon(){
//        PageFactory.initElements(Driver.getWebDriver(), this);
        Locator.initElements(this);
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver(), ELEMENT_VISIBILITY_TIME_OUT), this);
    }

    public Amazon searchItem(String item){
        String message = "Searching item : " + item;
        logger.info(message);
        ExtentReport.log(message);
        clearInputBox(searchBox);
        type(this.searchBox, item);
        scrollToElementAndTap(this.searchBtn);
        if(isElementVisible(this.tapHereToLogIn)){
            click(closeX);
        }
        return this;
    }

    public Amazon selectItemFromSearchResults(int itemNo){
        String message = "Selecting item from search results";
        logger.info(message);
        ExtentReport.log(message);
        String basePath = "//android.widget.TextView[@text='Results Check each product page for other buying options.']/..";
        By resultElementLocator = By.xpath(basePath + "/following-sibling::android.view.View[" + itemNo + "]");
        scrollToElement(resultElementLocator);
        tap(resultElementLocator);
        return this;
    }

    public Amazon addToCart(){
        String message = "Adding item to the cart";
        logger.info(message);
        ExtentReport.log(message);
        scrollToElementAndTap(addToCart);
        return this;
    }

    public void validateSuccessMessage(){
        String message = "Validating Add to Cart success message";
        logger.info(message);
        ExtentReport.log(message);
        verifyIfElementVisible(addToCartSuccessMessage);
    }

}
