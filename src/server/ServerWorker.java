package server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerWorker extends Thread {

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            System.out.println("Server started");
            while(true) {
                try {
                    Socket client = serverSocket.accept();
                    System.out.println(client.toString() + "connected");
                    Server server = new Server(client);
                    server.start();
                } catch (Exception ignore) {}
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
