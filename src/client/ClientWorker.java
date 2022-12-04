package client;

import java.io.IOException;
import java.util.Scanner;

public class ClientWorker extends Thread {
    private final Client client;
    private final Scanner inputScanner;
    String info;

    public ClientWorker() {
        client = new Client();
        inputScanner = new Scanner(System.in);
    }

    public void run() {
        boolean connected;
        try {
            ClientListener listener = new ClientListener(client);
            listener.start();
            do {
                info = inputScanner.nextLine();
                System.out.println(info);
                connected = client.sendMsg(info);
            } while (connected && !info.equals("STOP"));
            listener.stopListen();
            client.close();
            System.out.println("client close");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
