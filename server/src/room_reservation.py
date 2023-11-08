from entities.room import Room

class RoomReservation:

    def __init__(self):
        self.current_id = 0
        self.rooms = []
        
    def add_room(self, room: Room) -> bool:
        room.set_id(self.current_id)
        self.current_id += 1

        self.rooms.append(Room)
        return True

    def get_all_rooms(self):
        return self.rooms
