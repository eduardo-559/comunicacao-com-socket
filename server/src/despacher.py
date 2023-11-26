from src.room_reservation_skeleton import RoomReservationSkeleton

# QUESTION: Despachante como classe estática é uma boa prática?

class Despacher:

    # QUESTION: Usar um dicionario de metodos remotos é uma abordagem válida?
    remote_objects = {
        "RoomReservation": RoomReservationSkeleton,
    }

    @staticmethod
    def _get_class(class_name):
        if class_name in Despacher.remote_objects:
            return Despacher.remote_objects[class_name]
        
        else:
            ...
            # QUESTION: Como lidar caso o objeto remoto nao exista?

    @staticmethod
    def _get_method(class_object, method_name):
        if hasattr(class_object, method_name):
            method_reference = getattr(class_object, method_name)
            return method_reference
        else:
            ...
            # QUESTION: Como lidar caso o método remoto nao exista?

    @staticmethod
    def handle_request(request: dict):
        object_reference = request["objectReference"]
        method_id = request['methodId']
        
        class_object = Despacher.remote_objects[object_reference]
        method = Despacher._get_method(class_object, method_id)

        response_json = method(request["arguments"])
        return response_json

