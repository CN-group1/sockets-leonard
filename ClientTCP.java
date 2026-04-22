import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args) throws Exception {
        String serverIP = "100.84.71.66"; // IP-ul serverului
        int port = 5000;

        try (Socket socket = new Socket(serverIP, port)) {
            System.out.println("[CLIENT] Conectat la server!");

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("[TU]: ");
                String mesaj = scanner.nextLine();
                out.write(mesaj.getBytes(StandardCharsets.UTF_8));
                out.flush();

                byte[] buffer = new byte[1024];
                int bytes = in.read(buffer);
                String raspuns = new String(buffer, 0, bytes, StandardCharsets.UTF_8);
                System.out.println("[SERVER]: " + raspuns);

                if (mesaj.equalsIgnoreCase("exit")) break;
            }
        }
        System.out.println("[CLIENT] Conexiune inchisa.");
    }
}