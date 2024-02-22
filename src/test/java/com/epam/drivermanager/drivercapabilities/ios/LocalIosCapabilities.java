package com.epam.drivermanager.drivercapabilities.ios;

import org.openqa.selenium.MutableCapabilities;

public class LocalIosCapabilities implements IosDeviceCapabilities{

    public MutableCapabilities get(){
        return new MutableCapabilities();
    }
}
