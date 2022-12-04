package server;

import server.commands.*;

import java.io.*;
import java.net.Socket;

public class  Server extends Thread {
    private final Socket clientSocket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendMsg("Choose command" + System.lineSeparator());
            boolean connected = true;
            while (connected) {
                String msg = getMsg();
                if (msg == null || msg.equals("STOP")) {
                    connected = false;
                } else {
                    Command command;
                    String[] args = msg.split(" ");
                    if (args.length >= 1) {
                        switch (args[0]) {
                            case "AUTH" -> command = new Authenticate();
                            case "CREATE" -> command = new Create();
                            case "VIEW" -> command = new View();
                            case "EDIT" -> command = new Edit();
                            default -> throw new IllegalArgumentException("Unexpected value: " + args[0]);
                        }
                        msg = command.execute(this, msg);
                        sendMsg(msg);
                    }
                }
            }
            close();
            System.out.println("connection stopped");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMsg() throws IOException {
        return reader.readLine();
    }

    public void sendMsg(String command) throws IOException {
        writer.write(command + System.lineSeparator());
        writer.flush();
    }

    public void close() throws IOException {
        if (clientSocket != null) clientSocket.close();
        if (reader != null) reader.close();
        if (writer != null) writer.close();
    }
}
