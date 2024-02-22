package com.epam.pages.amazon;

import com.epam.drivermanager.Driver;
import com.epam.utils.locators.AndroidLocator;
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

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.constants.LocatorType.XPATH;
import static com.epam.constants.TimeOuts.ELEMENT_VISIBILITY_TIME_OUT;

public class Amazon extends UserAction {

    private static Logger logger = LogManager.getLogger(Amazon.class);

    /*@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='nav-search-keywords']")
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
    private WebElement closeX;*/

    @AndroidLocator(type = XPATH, value = "//android.widget.EditText[@resource-id='nav-search-keywords']")
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
    private By closeX;

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
        String itemDesc = getContentDesc(itemNo);
        By locatorToSearch = By.xpath("//android.view.View[@resource-id='search']/android.view.View/android.view.View/android.view.View[@content-desc=\"" + itemDesc + "\"]");
        scrollToElement(locatorToSearch);
        click(locatorToSearch);
        return this;
    }

    public Amazon addToCart(){
        String message = "Adding item to the cart";
        logger.info(message);
        ExtentReport.log(message);
        scrollToElement(addToCart);
        if(isElementVisible(this.tapHereToLogIn)){
            click(closeX);
        }
        tap(this.addToCart);
        return this;
    }

    public void validateSuccessMessage(){
        String message = "Validating Add to Cart success message";
        logger.info(message);
        ExtentReport.log(message);
        verifyIfElementVisible(addToCartSuccessMessage);
    }

    public String getContentDesc(int itemNo){
        List<String> itemDescList = new ArrayList<>();
        itemDescList.add("Sponsored Samsung Galaxy M34 5G (Prism Silver,6GB,128GB)|120Hz sAMOLED Display|50MP Triple No Shake Cam|6000 mAh Battery|4 Gen OS Upgrade & 5 Year Security Update|12GB RAM with RAM+|Android 13|Without Charger");
        itemDescList.add("Sponsored Samsung Galaxy M14 5G (Berry Blue,4GB,128GB)|50MP Triple Cam|Segment's Only 6000 mAh 5G SP|5nm Processor|2 Gen. OS Upgrade & 4 Year Security Update|12GB RAM with RAM Plus|Android 13|Without Charger");
        itemDescList.add("(Refurbished) Samsung Galaxy M53 5G (Emerald Brown, 6GB, 128GB Storage) |108MP Camera |sAmoled+ 120Hz |32MP Front Camera | 6nm Processor | 12GB RAM with RAM Plus |Travel Adapter to be Purchased Separately");
        itemDescList.add("(Refurbished) Samsung Galaxy M53 5G (Deep Ocean Blue, 6GB, 128GB Storage) |108MP Camera |sAmoled+ 120Hz |32MP Front Camera |6nm Processor |12GB RAM with RAM Plus |Travel Adapter to be Purchased Separately");
        itemDescList.add("(Refurbished) Samsung Galaxy M53 5G (Emerald Brown, 8GB, 128GB Storage) | 108MP Camera | sAmoled+ 120Hz | 32MP Front Camera | 6nm Processor | 16GB RAM with RAM Plus | Travel Adapter to be Purchased Separately");

        if(itemNo >= itemDescList.size()){
            return itemDescList.get(itemDescList.size()-1);
        }

        return itemDescList.get(itemNo-1);
    }

}
