package com.roomreservation;

public class Room {
    private Integer ID;
    private String description;
    private String roomNumber;
    private String sectionNumber;
    private Integer projectors;
    private Integer computers;
    private Boolean isOccupied;

    Room(String description, String roomNumber, String sectionNumber, Integer projectors, Integer computers){
        this.ID = -1;
        this.description = description;
        this.roomNumber = roomNumber;
        this.sectionNumber = sectionNumber;
        this.projectors = projectors;
        this.computers = computers;
        this.isOccupied = false;
    }

    public String getRoomAndSection(){
        return "Bloco " + this.sectionNumber + " - Sala " + this.roomNumber;
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