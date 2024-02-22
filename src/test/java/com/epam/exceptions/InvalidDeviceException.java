package com.epam.exceptions;

public class InvalidDeviceException extends RuntimeException{

    public InvalidDeviceException(){
        super("Given driver type is invalid. Please provide valid device type");
    }

    public InvalidDeviceException(String driver){
        super("Given driver type : " + driver + "is invalid. Please provide valid device type");
    }

}
