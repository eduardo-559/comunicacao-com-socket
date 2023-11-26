package com.roomreservation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
    private DatagramSocket SocketUDP;
    private InetAddress serverInetAddress;
    private String serverAdress;
    private Integer serverPort;

    UDPClient(){
        try{
            this.SocketUDP = new DatagramSocket();
            this.serverAdress = "localhost";
            this.serverPort = 3456;
            this.serverInetAddress = InetAddress.getByName(serverAdress);

        } catch (SocketException err){
            System.out.println(err.getMessage());
        } catch (UnknownHostException err) {
            System.out.println(err.getMessage());
        }
    }

    public String sendRequest(byte[] requestMessage){
        try{
            DatagramPacket requestPacket = new DatagramPacket(requestMessage, requestMessage.length, serverInetAddress, serverPort);
            SocketUDP.send(requestPacket);
            byte[] bufferResponse = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(bufferResponse, bufferResponse.length);
            SocketUDP.receive(responsePacket);
            String serverResponse = new String(responsePacket.getData(), 0, responsePacket.getLength());
            return serverResponse;

        }   catch (IOException err){
            System.out.println(err.getMessage());
        }
        return "";
    }
}
