package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class  Server extends Thread {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedWriter writer;
    private BufferedReader reader;

    public Server() {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean connect() {
        try {
            try {
                clientSocket = serverSocket.accept();
                writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String command = reader.readLine();
                if (command.equals("CONNECT")) {
                    System.out.println(command);
                    writer.write("CONNECT" + System.lineSeparator());
                    writer.flush();
                    return true;
                } else
                    return false;
            } catch (Exception e) {
                close();
                return false;
            }
        } catch (IOException ex) {
            return false;
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
        serverSocket.close();
        clientSocket.close();
        reader.close();
        writer.close();
    }
}
