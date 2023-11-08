package com.roomreservation;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class RoomReservationProxy {
    private Integer currentId = 0;
    private Gson gson = new Gson();

    private String serverAdress = "localhost";
    private Integer serverPort = 3456;

    RoomReservationProxy(){
        System.out.println("Created");
    }

    private String doOperation(String request){
        try{
            
            // Send Request
            DatagramSocket SocketUDP = new DatagramSocket();
            InetAddress serverInetAddress = InetAddress.getByName(serverAdress);

            byte[] jsonData = request.getBytes();

            DatagramPacket requestPacket = new DatagramPacket(jsonData, jsonData.length, serverInetAddress, serverPort);
            SocketUDP.send(requestPacket);

            // Receive Response
            byte[] bufferResponse = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(bufferResponse, bufferResponse.length);
            SocketUDP.receive(responsePacket);

            String serverResponse = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Server response = " + serverResponse);

            SocketUDP.close();
            return serverResponse;

        } catch (SocketException err) {
            System.out.println(err.getMessage());
        } catch (UnknownHostException err){
            System.out.println(err.getMessage());
        } catch (IOException err){
            System.out.println(err.getMessage());
        } 
        
        
        return "";
    }

    public Boolean addRoom(Room newRoom){
        String argumentsJson = gson.toJson(newRoom);

        Message requestMessage = new Message();

        requestMessage.messageType = 0;
        requestMessage.id = currentId++;
        requestMessage.objectReference = "RoomReservation";
        requestMessage.methodId = "addRoom";
        requestMessage.arguments = argumentsJson;

        String requestJson = gson.toJson(requestMessage);

        String response = doOperation(requestJson);
        if (response.equals("True")) return true;
        else return false;
    }
}
