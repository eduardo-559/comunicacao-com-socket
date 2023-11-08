import json
from src.room_reservation import RoomReservation
from entities.room import Room

# QUESTION: Esqueleto como classe estática é uma boa prática?
class RoomReservationSkeleton:

    room_reservation = RoomReservation()

    @staticmethod
    def add_room(args: str):
        json_args = json.loads(args)

        #QUESTION: É preciso fazer verificação de dados? (Se eles estão corretos, ou pode ser feito do lado do cliente?)

        new_room = Room(*json_args)

        return RoomReservationSkeleton.room_reservation.add_room(Room)

    @staticmethod
    def get_all_rooms(args: str):
        room_list = RoomReservationSkeleton.room_reservation.get_all_rooms()
        response_json = json.dumps(room_list)
        return response_json

    @staticmethod
    def search_room(args: str):
        ...
