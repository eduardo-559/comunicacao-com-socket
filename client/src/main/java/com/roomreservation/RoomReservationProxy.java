package com.roomreservation;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class RoomReservationProxy {
    private Integer currentId = 0;
    private Gson gson;
    private UDPClient udpClient;

    RoomReservationProxy(){
        this.gson = new Gson();
        this.udpClient = new UDPClient();
    }

    private String packsMessage(String objectReference, String methodId, String arguments){
        Message requestMessage = new Message(0, currentId++, objectReference, methodId, arguments);

        requestMessage.messageType = 0;
        requestMessage.id = currentId++;
        requestMessage.objectReference = objectReference;
        requestMessage.methodId = methodId;
        requestMessage.arguments = arguments;

        return gson.toJson(requestMessage);
    }

    private String unpackMessage(String responseMessage){
        Message response = gson.fromJson(responseMessage, Message.class);
        return response.arguments;
    }

    private String doOperation(String objectReference, String method, String args){
        String jsonRequest = this.packsMessage(objectReference, method, args);
        udpClient.sendRequest(jsonRequest.getBytes());

        String response = udpClient.getReply();

        while(response == "TIMEOUTED"){
            udpClient.sendRequest(jsonRequest.getBytes());
            response = udpClient.getReply();
        }

        String responseArgs = this.unpackMessage(response);
        return responseArgs;        
    }

    public Boolean addRoom(Room newRoom){
        String requestArgs = gson.toJson(newRoom);
        String responseArgs = this.doOperation("RoomReservation", "addRoom", requestArgs);
        
        if (responseArgs.equals("sucess")){
            return true;
        } else {
            return false;
        }
    }

    public Room searchRoom(String sectionNumber, String roomNumber) throws RoomNotExistsException{
        String[] argsList = {sectionNumber, roomNumber};
        String requestArgs = gson.toJson(argsList);
        String responseArgs = this.doOperation("RoomReservation", "searchRoom", requestArgs);

        if (responseArgs.equals("RoomNotExists")){
            throw new RoomNotExistsException("Error: A sala buscada não existe.");
        } else {
            return gson.fromJson(responseArgs, Room.class);
        }
    }

    public List<Room> getAllRooms() throws RoomNotExistsException {
        String responseArgs = this.doOperation("RoomReservation", "getAllRooms", "");
    
        if (responseArgs.equals("RoomNotExists")){
            throw new RoomNotExistsException("Error: Não existe salas cadastradas.");
        } else {
            Type listType = new TypeToken<List<Room>>() {}.getType();
            List<Room> listRooms = gson.fromJson(responseArgs, listType);
            return listRooms;
        }
    }

    public Room removeRoom(Integer ID) throws RoomNotExistsException {
        String requestArgs = gson.toJson(ID);
        String responseArgs = this.doOperation("RoomReservation", "removeRoom", requestArgs);

        if (responseArgs.equals("RoomNotExists")){
            throw new RoomNotExistsException("Error: A sala não existe.");
        } else {
            Room room = gson.fromJson(responseArgs, Room.class);
            return room;
        }
    }

    public void reserveRoom(Integer ID) throws RoomNotExistsException, InvalidOccupancyStatusException {
        String requestArgs = gson.toJson(ID);
        String responseArgs = this.doOperation("RoomReservation", "reserveRoom", requestArgs);

        if (responseArgs.equals("RoomNotExists")){
            throw new RoomNotExistsException("Error: A sala não existe.");
        } else if (responseArgs.equals("InvalidOccupancyStatus")){
            throw new InvalidOccupancyStatusException("Error: Sala ja está reservada.");
        }
    }

    public void cancelReservation(Integer ID) throws RoomNotExistsException, InvalidOccupancyStatusException {
        String requestArgs = gson.toJson(ID);
        String responseArgs = this.doOperation("RoomReservation", "cancelReservation", requestArgs);

        if (responseArgs.equals("RoomNotExists")){
            throw new RoomNotExistsException("Error: A sala não existe.");
        } else if (responseArgs.equals("InvalidOccupancyStatus")){
            throw new InvalidOccupancyStatusException("Error: Sala não está reservada.");
        }
    }
}
