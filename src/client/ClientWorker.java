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
        boolean connected = client.connect();
        try {
            ClientListener listener = new ClientListener(client);
            listener.start();
            while (connected && !info.equals("STOP")) {
                info = inputScanner.nextLine();
                info = info.replaceAll("\\s+", "_");
                System.out.println(info);
                client.sendMsg(info);
            }
            listener.stopListen();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
