import json
from src.room_reservation import RoomReservation
from entities.room import Room


class RoomReservationSkeleton:

    room_reservation = RoomReservation()

    @staticmethod
    def addRoom(args: str):
        new_room = json.loads(args, object_hook= lambda dct : Room(**dct))
        RoomReservationSkeleton.room_reservation.addRoom(new_room)
        
        return "sucess"
    
    @staticmethod
    def searchRoom(args: str):
        args_list = json.loads(args)
        room_searched = RoomReservationSkeleton.room_reservation.searchRoom(args_list[0], args_list[1])

        if room_searched is not None:
            room_json = json.dumps(room_searched.__dict__)
            return room_json
        else:
            return "RoomNotExists"

    @staticmethod
    def getAllRooms(args: str):
        room_list = RoomReservationSkeleton.room_reservation.getAllRooms()

        if len(room_list) > 0:
            response_json = json.dumps([room.__dict__ for room in room_list])
            return response_json
        else:
            return "RoomNotExists"

    @staticmethod
    def removeRoom(args: str):
        removed_room = RoomReservationSkeleton.room_reservation.removeRoom(args)

        if removed_room is not None:
            room_json = json.dumps(removed_room.__dict__)
            return room_json
        else:
            return "RoomNotExists"
        
    @staticmethod
    def reserveRoom(args: str):
        reserve_status = RoomReservationSkeleton.room_reservation.reserveRoom(args)

        if reserve_status is not None:
            if reserve_status:
                return "sucess"
            else:
                return "InvalidOccupancyStatus"
        else:
            return "RoomNotExists"
        
    @staticmethod
    def cancelReservation(args: str):
        reserve_status = RoomReservationSkeleton.room_reservation.cancelReservation(args)

        if reserve_status is not None:
            if reserve_status:
                return "sucess"
            else:
                return "InvalidOccupancyStatus"
        else:
            return "RoomNotExists"
        
    

        
