import socket

HOST = '0.0.0.0'  # IP-ul tău Tailscale
PORT = 5000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind((HOST, PORT))
    s.listen()
    print(f"[SERVER] Astept conexiuni pe portul {PORT}...")

    conn, addr = s.accept()
    with conn:
        print(f"[SERVER] Conectat de la {addr}")
        print("[SERVER] Scrie un mesaj (sau 'exit' pentru a inchide):")
        
        while True:
            data = conn.recv(1024)
            if not data:
                break
            mesaj = data.decode('utf-8')
            print(f"[CLIENT]: {mesaj}")
            
            raspuns = input("[TU]: ")
            conn.sendall(raspuns.encode('utf-8'))
            
            if raspuns.lower() == 'exit':
                break

print("[SERVER] Conexiune inchisa.")