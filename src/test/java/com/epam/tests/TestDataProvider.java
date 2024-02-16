package com.epam.tests;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "Item checkout details")
    public String[][] itemCheckOutData(){
        return new String[][]{
                {"All Items", "Name (A to Z)", "Sauce Labs Bike Light", "$9.99", "Habeeb", "Mohamed", "612536"}
        };
    }
}
