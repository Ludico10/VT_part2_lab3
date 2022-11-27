package server;

public class ServerWorker {
    private Server server;

    public ServerWorker() {
        server = new Server();
    }

    public void start() {
        boolean connected = false;
        while (!connected)
            connected = server.connect();
        try {
            server.sendMsg("Please, Login" + System.lineSeparator());
            while (connected) {
                String msg = server.getMsg();
                if (msg.equals("STOP")) {
                    connected = false;
                }
            }
            server.close();
        } catch (Exception ignored) {

        }
    }
}
