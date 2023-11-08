package com.roomreservation;

import java.util.Scanner;

class App {

    public static Boolean inLooping = true;
    public static Scanner sc = new Scanner(System.in);
    public static RoomReservationProxy reservation = new RoomReservationProxy();
    
    public static void printMenu(){
        System.out.println("\n[Room Reservation System]");
        System.out.println("1 - Lista de Salas");
        System.out.println("2 - Buscar Sala");
        System.out.println("3 - Adicionar Sala");
        System.out.println("4 - Remover Sala");
        System.out.println("5 - Reservar Sala");
        System.out.println("6 - Encerrar Reserva");
        System.out.println("7 - Exit\n");
        System.out.print(">> ");
    }

    public static void interpretInput(String operation){
        switch (operation) {    
            case "1":
                System.out.println("Case 1");
                break;
        
            case "2":
                break;

            case "3":
                System.out.println("Qual o bloco da sala? ");
                String roomSection = sc.next();
                System.out.println("Qual o numero da sala?");
                String roomNumber = sc.next();
            
                Room newRoom = new Room(roomSection, roomNumber);
                Boolean result = reservation.addRoom(newRoom);
                
                if (result){
                    System.out.println("Adicionado com sucesso!");
                } else {
                    System.out.println("Não foi possivel adicionar!");
                }
                break;

            case "4":
                break;
        
            case "5":
                break;

            case "6":
                break;

            case "7":
                inLooping = false;
                break;

            default:
                break;
        }
    }
    
    public static void main(String[] args){

        String operation;
        while(inLooping){
            printMenu();
            operation = sc.next();
            interpretInput(operation);
        }

        sc.close();
    }
}
