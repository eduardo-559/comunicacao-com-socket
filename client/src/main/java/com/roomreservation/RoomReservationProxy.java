package com.roomreservation;

import com.google.gson.Gson;

public class RoomReservationProxy {
    private Integer currentId = 0;
    private Gson gson;
    private UDPClient udpClient;

    RoomReservationProxy(){
        this.gson = new Gson();
        this.udpClient = new UDPClient();
    }

    private String packsMessage(Integer messageType, String methodId, String arguments){
        Message requestMessage = new Message();

        requestMessage.messageType = 0;
        requestMessage.id = currentId++;
        requestMessage.objectReference = "RoomReservation";
        requestMessage.methodId = methodId;
        requestMessage.arguments = arguments;

        return gson.toJson(requestMessage);
    }

    private String doOperation(String request){
        byte[] jsonData = request.getBytes();
        String response = udpClient.sendRequest(jsonData);
        return response;        
    }

    public Boolean addRoom(Room newRoom){
        String argumentsJson = gson.toJson(newRoom);
        String requestJson = this.packsMessage(0, "addRoom", argumentsJson);
        String response = doOperation(requestJson);
        if (!response.isEmpty()) return true;
        else return false;
    }
}
