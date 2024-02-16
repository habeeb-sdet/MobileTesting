package com.epam.pages;

import com.epam.constants.TimeOuts;
import com.epam.drivermanager.Driver;
import com.epam.utils.locators.AndroidLocator;
import com.epam.utils.locators.Locator;
import com.epam.utils.UserAction;
import com.epam.utils.reporting.LogManager;
import com.epam.utils.reporting.Logger;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static com.epam.constants.LocatorType.XPATH;

public class LogIn {

    private static Logger logger = LogManager.getLogger(LogIn.class);

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
    private WebElement username;

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
    private WebElement password;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
    private WebElement loginBtn;

    public LogIn(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver(), TimeOuts.ELEMENT_VISIBILITY_TIME_OUT), this);
    }

    public LogIn enterUsername(String username){
        logger.info("Enter username to login : " + username);
        UserAction.type(this.username, username);
        return this;
    }

    public LogIn enterPassword(String password){
        logger.info("Enter password to login : " + username);
        UserAction.type(this.password, password);
        return this;
    }

    public Products clickLogIn(){
        logger.info("click login button");
        UserAction.click(this.loginBtn);
        return new Products();
    }
}
