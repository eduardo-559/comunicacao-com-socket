class Room:
    def __init__(self, section_number, room_number):
        self.ID = -1
        self.room_number = room_number
        self.section_number = section_number
        self.description = ""
        self.projectors = 0
        self.computers = 0
        self.is_occupied = False

    def get_room_and_section(self):
        return f"Bloco {self.section_number} - Sala {self.room_number}"

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
