package server;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started on port 1234");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected!");
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Received: " + input);
                out.println("ACK: " + input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
