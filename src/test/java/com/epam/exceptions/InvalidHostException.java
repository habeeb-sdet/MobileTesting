package com.epam.exceptions;

public class InvalidHostException extends RuntimeException {

    public InvalidHostException(){
        super("Invalid host address");
    }

    public InvalidHostException(String message){
        super(message);
    }
}
