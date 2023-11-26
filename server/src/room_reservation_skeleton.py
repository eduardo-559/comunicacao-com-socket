import json
from src.room_reservation import RoomReservation
from entities.room import Room


class RoomReservationSkeleton:

    room_reservation = RoomReservation()

    @staticmethod
    def addRoom(args: str):
        json_args = json.loads(args)

        new_room = Room(json_args["roomNumber"], json_args["sectionNumber"])

        return RoomReservationSkeleton.room_reservation.addRoom(new_room)

    @staticmethod
    def get_all_rooms(args: str):
        room_list = RoomReservationSkeleton.room_reservation.get_all_rooms()
        response_json = json.dumps(room_list)
        return response_json

    @staticmethod
    def search_room(args: str):
        ...
