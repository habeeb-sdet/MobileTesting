package com.epam.tests;

import com.epam.pages.LogIn;
import com.epam.pages.Products;
import com.epam.utils.poc.MobileScenarios;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AppTests {

    private String username = "standard_user";
    private String password = "secret_sauce";

    @BeforeClass
    public void logIn(){
        LogIn logIn = new LogIn();
        logIn
                .enterUsername(this.username)
                .enterPassword(this.password)
                .clickLogIn();
    }

    /*@Test(description = "Validate Login",
            dataProviderClass= TestDataProvider.class, dataProvider = "Item checkout details",  alwaysRun = true)
    public void checkoutThroughNativeApp(String menuOption, String sortBy, String itemName, String itemPrice, String firstName,
                              String lastName, String postalCode) {
        Products products = new Products();
        products
                .validateProductPage()
                .selectMenu()
                .selectAllItems()
                .addItemUsingDragAndDrop(itemName)
                .sortItems(sortBy)
                .selectItem(itemName)
                .addToCart()
                .moveToCartPage()
                .validateItemDetails(itemName, itemPrice)
                .checkOut()
                .fillCheckoutDetails(firstName, lastName, postalCode)
                .continueToCheckout()
                .finishCheckout()
                .verifyThankYou()
                .backToHome();
    }

    @Test(description = "Validate Adding item to cart from Web View", alwaysRun = true)
    public void checkoutThroughWebView() {
        String url = "https://www.amazon.in";
        String itemToSearch = "Samsung Mobile M53";

        Products products = new Products();
        products
                .selectMenu()
                .selectWebView()
                .enterUrl(url)
                .tapOnGoToSite()
                .searchItem(itemToSearch)
                .selectItemFromSearchResults(2)
                .addToCart()
                .validateSuccessMessage();
    }*/

    @Test(description = "Validate Adding item to cart from Web View", alwaysRun = true)
    public void mobileScenariosPractice() {
        String url = "https://www.amazon.in";
        String itemToSearch = "Samsung Mobile M53";

        Products products = new Products();
        products
                .selectMenu();

        MobileScenarios mobileScenarios = new MobileScenarios();
//        mobileScenarios.switchBetweenApps();
//        mobileScenarios.readNotifications();
//        mobileScenarios.getPerformanceData();
//        mobileScenarios.putAppInBackground();
//        mobileScenarios.enableWifi();
//        mobileScenarios.unlockThePhone();
//        mobileScenarios.permissions();

        mobileScenarios.enableBluetooth();
    }

}
