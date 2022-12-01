import client.ClientWorker;
import server.ServerWorker;

public class Main {
    public static void main(String[] args) {
        ClientWorker client = new ClientWorker();
        client.start();
        ServerWorker server = new ServerWorker();
        server.start();
    }
}