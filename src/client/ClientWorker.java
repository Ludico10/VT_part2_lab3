package client;

import java.io.IOException;
import java.util.Scanner;

public class ClientWorker {
    private final Client client;
    private final Scanner inputScanner;
    String info;

    public ClientWorker() {
        client = new Client();
        inputScanner = new Scanner(System.in);
    }

    public void start() {
        boolean connected = client.connect();
        try {
            while (connected && !info.equals("STOP")) {
                info = inputScanner.nextLine();
                info = info.replaceAll("\\s+", "_");
                System.out.println(info);
                client.sendMsg(info);
            }
            client.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
