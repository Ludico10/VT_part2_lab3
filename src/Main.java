import client.ClientWorker;
import server.ServerWorker;

public class Main {
    public static void main(String[] args) {
        ServerWorker server = new ServerWorker();
        server.start();
        ClientWorker client = new ClientWorker();
        client.start();
    }
}