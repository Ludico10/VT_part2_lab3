package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Client() {
        try {
            try {
                socket = new Socket("localhost", 8888);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (Exception e) {
                close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean connect() {
        try {
            String word = "CONNECT" + System.lineSeparator();
            writer.write(word);
            writer.flush();
            return true;
        } catch (IOException e){
            return false;
        }
    }

    public void sendMsg(String command) throws IOException {
        writer.write(command + System.lineSeparator());
        writer.flush();
    }

    public String getMsg() throws IOException {
            return reader.readLine();
    }

    public void close() throws IOException {
        socket.close();
        reader.close();
        writer.close();
    }
}
