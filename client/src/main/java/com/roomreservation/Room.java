package com.roomreservation;

public class Room {
    private Integer ID;
    private String description;
    private String roomNumber;
    private String sectionNumber;
    private Integer projectors;
    private Integer computers;
    private Boolean isOccupied;

    Room(String roomNumber, String sectionNumber){
        this.ID = -1;
        this.roomNumber = roomNumber;
        this.sectionNumber = sectionNumber;
        this.description = "Default";
        this.projectors = 0;
        this.computers = 0;
        this.isOccupied = false;
    }

    public String getRoomAndSection(){
        return "Sala " + this.roomNumber + " - Bloco " + this.sectionNumber;
    }

    public Integer getID(){
        return this.ID;
    }

    public String getDescription(){
        return this.description;
    }

    public Integer getNumberOfProjectors(){
        return this.projectors;
    }

    public Integer getNumberOfComputers(){
        return this.computers;
    }

    public Boolean isOccupied(){
        return this.isOccupied;
    }

}