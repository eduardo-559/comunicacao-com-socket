import threading
import json
import socket as sk
from src.despacher import Despacher

def handle_client(client_adress, client_request, socket_udp):
    # TODO: Usar "MessageHistoric" para tratar duplicamento de mensagens

    client_request = client_request.decode('utf-8')
    client_request_json = json.loads(client_request)

    # QUESTION: Há problemas em enviar a requisição já desserializada para o despachante?
    response_args = Despacher.handle_request(client_request)

    server_response = {
        "messageType": 1,
        "requestId":  client_request_json["requestId"],
        "objectReference": client_request_json["objectReference"],
        "methodId": client_request_json["methodId"],
        "arguments": response_args
    }

    socket_udp.sendto(server_response.encode(), client_adress)

def main():
    server_adress = ("localhost", 3456)
    socket_udp = sk.socket(sk.AF_INET, sk.SOCK_DGRAM)
    socket_udp.bind(server_adress)

    print(f"Server online in {server_adress[0]}:{server_adress[1]}")

    while True:
        client_request, client_adress = socket_udp.recvfrom(1024)
        client_thread = threading.Thread(target=handle_client, args=(client_adress, 
                                                                     client_request, 
                                                                     socket_udp))
        client_thread.start()


if __name__ == "__main__":
    main()
