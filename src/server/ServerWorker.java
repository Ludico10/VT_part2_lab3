package server;

import server.commands.Command;
import server.commands.View;

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
                if (msg == null || msg.equals("STOP")) {
                    connected = false;
                }
                Command command;
                var args = msg.split(" ");
                if (args.length >= 1) {
                     switch (args[0]) {
                        case "AUTH"			-> command = new Authenticate();
                        case "CREATE"		-> command = new Create();
                        case "DISCONNECT"	-> command = new Disconnect();
                        case "EDIT" 		-> command = new Edit();
                        case "VIEW"			-> command = new View();
                        default				-> throw new IllegalArgumentException("Unexpected value: " + args[0]);
                    };
                    msg = command.execute(msg);
                    server.sendMsg(msg);
                }
            }
            server.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
