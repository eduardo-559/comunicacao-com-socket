package com.roomreservation;

public class InvalidOccupancyStatusException extends Exception {
    public InvalidOccupancyStatusException(String message){
        super(message);
    }
}
