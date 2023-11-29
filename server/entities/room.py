import json

class Room:
    def __init__(self, ID, description, roomNumber, sectionNumber, projectors, computers, isOccupied):
        self.ID = ID
        self.description = description
        self.roomNumber = roomNumber
        self.sectionNumber = sectionNumber
        self.projectors = projectors
        self.computers = computers
        self.isOccupied = isOccupied

    def get_room_and_section(self):
        return f"Bloco {self.sectionNumber} - Sala {self.roomNumber}"

    def set_id(self, new_id) -> None:
        self.ID = new_id

    def get_number_of_projectors(self) -> int:
        return self.projectors

    def get_number_of_computers(self) -> int:
        return self.computers

    def is_occupied(self) -> bool:
        return self.isOccupied
    
    def set_occupied(self, status) -> bool:
        self.isOccupied = status

    def __str__(self) -> str:
        return f"{self.ID} - {self.description}"
