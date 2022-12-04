package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Client() {
        try {
            try {
                socket = new Socket(InetAddress.getLocalHost(), 5555);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (Exception e) {
                close();
                e.printStackTrace();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean sendMsg(String command) {
        try {
            writer.write(command + System.lineSeparator());
            writer.flush();
            return true;
        } catch (IOException e){
            return false;
        }
    }

    public String getMsg() throws IOException {
            return reader.readLine();
    }

    public void close() throws IOException {
        if (socket != null) socket.close();
        if (reader != null) reader.close();
        if (writer != null) writer.close();
    }
}
