package server;

import server.commands.*;

public class ServerWorker {
    private final Server server;

    public ServerWorker() {
        server = new Server();
    }

    public void start() {
        boolean connected = false;
        while (!connected)
            connected = server.connect();
        try {
            server.sendMsg("Choose command" + System.lineSeparator());
            while (connected) {
                String msg = server.getMsg();
                if (msg == null || msg.equals("STOP")) {
                    connected = false;
                }
                Command command;
                assert msg != null;
                String[] args = msg.split(" ");
                if (args.length >= 1) {
                     switch (args[0]) {
                        case "AUTH"	-> command = new Authenticate();
                        case "CREATE" -> command = new Create();
                        case "VIEW"	-> command = new View();
                        case "EDIT" -> command = new Edit();
                        default	-> throw new IllegalArgumentException("Unexpected value: " + args[0]);
                    };
                    msg = command.execute(this, msg);
                    server.sendMsg(msg);
                }
            }
            server.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
