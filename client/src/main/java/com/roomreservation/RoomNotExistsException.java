package com.roomreservation;

public class RoomNotExistsException extends Exception {
    public RoomNotExistsException(String message){
        super(message);
    }
}
