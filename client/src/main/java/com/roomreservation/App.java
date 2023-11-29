package com.roomreservation;

import java.util.Scanner;
import java.util.List;

class App {

    public static Boolean inLooping = true;
    public static Scanner sc = new Scanner(System.in);
    public static RoomReservationProxy reservation = new RoomReservationProxy();
    
    public static void printMenu(){
        System.out.println("\n[Room Reservation System]");
        System.out.println("1 - Listar Salas");
        System.out.println("2 - Buscar Sala");
        System.out.println("3 - Adicionar Sala");
        System.out.println("4 - Remover Sala");
        System.out.println("5 - Reservar Sala");
        System.out.println("6 - Encerrar Reserva");
        System.out.println("7 - Exit\n");
        System.out.print(">> ");
    }

    private static void printRoom(Room room){
        System.out.println(room.getRoomAndSection());
        System.out.println("ID: " + room.getID());
        System.out.println("Qtd. Computadores: " + room.getNumberOfComputers());
        System.out.println("Qtd. Projetores: " + room.getNumberOfProjectors());
        System.out.println("Descrição: " + room.getDescription());
        System.out.print("Status: ");

        if (room.isOccupied()){
            System.out.println("Ocupada");
        } else {
            System.out.println("Desocupada");
        }
        System.out.println();
    }  

    public static void interpretInput(String operation){
        System.out.println();
        switch (operation) {    
            case "1":
                try{
                    List<Room> allRooms = reservation.getAllRooms();

                    System.out.println("[Listar Salas]");
                    for (Room room : allRooms){
                        printRoom(room);
                    }


                } catch (RoomNotExistsException err) {
                    System.out.println("Não há salas cadastradas no sistema.");
                }
                
                break;
            case "2":
                System.out.println("[Buscar Sala]");
                System.out.print(" > Qual o bloco? ");
                String sctNumber = sc.next();
                System.out.print(" > Qual a sala? ");
                String rmNumber = sc.next();

                try{
                    Room result = reservation.searchRoom(sctNumber, rmNumber);
                    printRoom(result);
                } catch (RoomNotExistsException err) {
                    System.out.println("A sala buscada não existe.");
                }
                
                break;

            case "3":
            System.out.println("[Adicionar Sala]");
                System.out.print(" > Qual o bloco da sala? ");
                String sectionNumber = sc.next();
                System.out.print(" > Qual o numero da sala? ");
                String roomNumber = sc.next();
                System.out.print(" > Qual o numero de projetores? ");
                Integer projectors = sc.nextInt();
                System.out.print(" > Qual o numero de computadores da sala? ");
                Integer computers = sc.nextInt(); sc.nextLine();
                System.out.print(" > Digite uma Descição: ");
                String description = sc.nextLine();
                
                Room newRoom = new Room(description, roomNumber, sectionNumber, projectors, computers);
                Boolean result = reservation.addRoom(newRoom);
                
                if (result){
                    System.out.println("Adicionado com sucesso!");
                } else {
                    System.out.println("Não foi possivel adicionar!");
                }

                break;

            case "4":
                System.out.println("[Remover Sala]");
                System.out.print(" > Qual o ID? ");
                Integer ID = sc.nextInt();
                
                try{
                    Room removedRoom = reservation.removeRoom(ID);
                    System.out.println("A Seguinte Sala foi Removida com Sucesso:");
                    printRoom(removedRoom);
                } catch (RoomNotExistsException err) {
                    System.out.println("A sala não existe!");
                }

                break;
        
            case "5":
                System.out.println("[Reservar Sala]");
                System.out.println(" > Qual o ID? ");
                Integer id = sc.nextInt();

                try {
                    reservation.reserveRoom(id);

                    System.out.println("Sala Reservada com Sucesso!");
                } catch (RoomNotExistsException err){
                    System.out.println("A sala não existe!");
                } catch (InvalidOccupancyStatusException err){
                    System.out.println("A sala já está reservada!");
                }

                break;

            case "6":
                System.out.println("[Cancelar Reserva]");
                System.out.println(" > Qual o ID? ");
                Integer IDRoom = sc.nextInt();

                try {
                    reservation.cancelReservation(IDRoom);
                    System.out.println("Reserva cancelada com Sucesso!");
                } catch (RoomNotExistsException err){
                    System.out.println("A sala não existe!");
                } catch (InvalidOccupancyStatusException err){
                    System.out.println("A sala não está reservada!");
                }
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
