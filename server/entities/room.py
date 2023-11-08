class Room:
    def __init__(self, room_number, section_number, description, projectors, computers):
        self.ID = -1
        self.room_number = room_number
        self.section_number = section_number
        self.description = description
        self.projectors = projectors
        self.computers = computers
        self.is_occupied = False

    def set_id(self, new_id) -> None:
        self.ID = new_id

    def get_number_of_projectors(self) -> int:
        return self.projectors

    def get_number_of_computers(self) -> int:
        return self.computers

    def is_occupied(self) -> bool:
        return self.occupied

    def __str__(self) -> str:
        return f"{self.ID} - {self.description}"
