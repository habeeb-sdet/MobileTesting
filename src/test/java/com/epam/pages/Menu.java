package com.epam.pages;

import com.epam.utils.UserAction;
import com.epam.utils.reporting.ExtentReport;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import org.openqa.selenium.By;

public class Menu {

    private static Logger logger = LogManager.getLogger(Menu.class);

    public void selectMenuOption(String option){
        String path = String.format("//android.view.ViewGroup[@content-desc='test-%s']", option.toUpperCase());
        UserAction.click(By.xpath(path));
    }

    public Products selectAllItems(){
        String message = "Selecting \"All Items\" from Menu list";
        logger.info(message);
        ExtentReport.log(message);
        selectMenuOption("All Items");
        return new Products();
    }

    public WebView selectWebView(){
        String message = "Selecting \"WebView\" from Menu list";
        logger.info(message);
        ExtentReport.log(message);
        selectMenuOption("WebView");
        return new WebView();
    }

    public void selectMenuOptionCheck(String option){
        String path = String.format("//android.view.ViewGroup[@content-desc='test-%s']", option.toUpperCase());
        UserAction.click(By.xpath(path));
    }

}
