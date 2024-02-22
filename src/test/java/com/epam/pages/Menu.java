package com.epam.pages;

import com.epam.constants.DriverType;
import com.epam.constants.TestProps;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import org.openqa.selenium.By;

import static com.epam.constants.DriverType.ANDROID;
import static com.epam.constants.DriverType.IOS;

public class Menu {

    private static Logger logger = LogManager.getLogger(Menu.class);

    /**
     * Select the option from the menu list
     * @param option option to select from Menu List
     */
    public void selectMenuOption(String option){
        option = option.toUpperCase();
        By path = null;
        if(TestProps.getPlatform() == IOS){
            path = By.xpath("//XCUIElementTypeOther[@name='test-" + option + "']");
        }else if(TestProps.getPlatform() == ANDROID){
            path = By.xpath("//android.view.ViewGroup[@content-desc='test-" + option + "']");
        }
        UserAction.click(path);
    }

    /**
     * Select All Items from Menu List
     * @return Products page
     */
    public Products selectAllItems(){
        String message = "Selecting \"All Items\" from Menu list";
        logger.info(message);
        ExtentReport.log(message);
        selectMenuOption("All Items");
        return new Products();
    }

    /**
     * Select Web View option from Menu List
     * @return
     */
    public WebView selectWebView(){
        String message = "Selecting \"WebView\" from Menu list";
        logger.info(message);
        ExtentReport.log(message);
        selectMenuOption("WebView");
        return new WebView();
    }

    /**
     * Select Menu Option
     * @param option
     */
    public void selectMenuOptionCheck(String option){
        String path = String.format("//android.view.ViewGroup[@content-desc='test-%s']", option.toUpperCase());
        UserAction.click(By.xpath(path));
    }

}
