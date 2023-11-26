import threading
import json
import socket as sk
from src.despacher import Despacher

# Nao precisa ser multithread
def handle_client(client_adress, client_request, socket_udp):
    # TODO: Usar "MessageHistoric" para tratar duplicamento de mensagens

    client_request = client_request.decode('utf-8')
    client_request_json = json.loads(client_request)

    response_args = Despacher.handle_request(client_request_json)

    response_args = json.dumps(response_args)

    server_response = {
        "messageType": 1,
        "id":  client_request_json["id"],
        "objectReference": client_request_json["objectReference"],
        "methodId": client_request_json["methodId"],
        "arguments": response_args
    }

    server_response = json.dumps(server_response)
    socket_udp.sendto(server_response.encode(), client_adress)

def main():
    server_adress = ("localhost", 3456)
    socket_udp = sk.socket(sk.AF_INET, sk.SOCK_DGRAM)
    socket_udp.bind(server_adress)

    print(f"Server online in {server_adress[0]}:{server_adress[1]}")

    while True:
        client_request, client_adress = socket_udp.recvfrom(1024)
        handle_client(client_adress, client_request, socket_udp)


if __name__ == "__main__":
    main()
