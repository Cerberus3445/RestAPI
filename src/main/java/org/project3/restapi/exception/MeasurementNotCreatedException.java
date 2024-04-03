package org.project3.restapi.exception;

public class MeasurementNotCreatedException extends RuntimeException{
    public MeasurementNotCreatedException(String message){
        super(message);
    }
}
