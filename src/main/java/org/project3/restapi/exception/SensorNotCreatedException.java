package org.project3.restapi.exception;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String message){
        super(message);
    }
}
