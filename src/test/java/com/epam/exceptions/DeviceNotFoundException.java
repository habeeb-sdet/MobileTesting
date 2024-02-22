package com.epam.exceptions;

public class DeviceNotFoundException extends RuntimeException{

    public DeviceNotFoundException(){
        super("Device not found");
    }

    public DeviceNotFoundException(String osVersion){
        super("Device with os version " + osVersion + " not found in Nodes.json file");
    }
}
