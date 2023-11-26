from entities.room import Room

class RoomReservation:

    def __init__(self):
        self.current_id = 0
        self.rooms = []
        
    def addRoom(self, room: Room) -> bool:
        room.set_id(self.current_id)
        self.current_id += 1

        self.rooms.append(room)
        print("[Salas cadastradas]")
        for room in self.rooms:
            print(room.get_room_and_section())

        return True

    def get_all_rooms(self):
        return self.rooms
