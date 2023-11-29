from entities.room import Room

class RoomReservation:

    def __init__(self):
        self.current_id = 0
        self.rooms = []
        
    def addRoom(self, room: Room) -> bool:
        room.set_id(self.current_id)
        self.current_id += 1

        self.rooms.append(room)
        for room in self.rooms:
            print(room.get_room_and_section())


    def searchRoom(self, sectionNumber, roomNumber):
        for room in self.rooms:
            if room.sectionNumber == sectionNumber and room.roomNumber == roomNumber:
                return room
        return None

    def getAllRooms(self):
        return self.rooms
    
    def removeRoom(self, IDRoom):
        for index, room in enumerate(self.rooms):
            if room.ID == int(IDRoom):
                return self.rooms.pop(index)

        return None
    
    def reserveRoom(self, IDRoom):
        for room in self.rooms:
            if room.ID == int(IDRoom):
                if room.is_occupied():
                    return False
                else:
                    room.set_occupied(True)
                    return True

        return None
    
    def cancelReservation(self, IDRoom):
        for room in self.rooms:
            if room.ID == int(IDRoom):
                if not room.is_occupied():
                    return False
                else:
                    room.set_occupied(False)
                    return True

        return None