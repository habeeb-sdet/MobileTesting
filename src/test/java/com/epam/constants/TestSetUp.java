package com.epam.constants;

import lombok.Data;
import lombok.Getter;

public class TestSetUp {

    public enum Platform {
        LOCAL_SERVICE, GRID, SAUCE_LABS
    }

    @Data
    public static class Url{
        @Getter
        private static final String GRID_URL = "http://localhost:4444";
        @Getter
        private static final String SAUCE_LAB_URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    }

}