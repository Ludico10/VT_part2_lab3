package client;

public class ClientListener extends Thread{
    public boolean inProc = true;
    public Client client;

    public ClientListener(Client client) {
        this.client = client;
    }

    public void run() {
        while (inProc) {
            try {
                String msg = client.getMsg();
                System.out.println(msg);
            }
            catch (Exception e) {
                System.out.println("Sending error");
            }
        }
    }

    public void stopListen() {
        inProc = false;
    }

}
