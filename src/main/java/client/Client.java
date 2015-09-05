package client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by jonathan on 4-9-15.
 * maakt connectie met server, daarna start deze threads waarin gecommuniceerd kan worden met de server.
 */
public class Client {

    private ClientRecieveThread recieveThread;
    private ClientSendThread sendThread;

    private final String HOST_NAME = "localhost";
    private final int HOST_PORT = 2000;

    private final String username;

    public String getUsername() {
        return username;
    }

    public static void main(String[] args) {

        new Client("joo" + Math.random()).run();

    }

    public Client(String username) {
        this.username = username;
    }

    private void run(){
        try {
            Socket socket = new Socket(HOST_NAME, HOST_PORT);
            System.out.println("Client: socket connection established with server");

            // start the threads
            recieveThread = new ClientRecieveThread(socket);
            sendThread = new ClientSendThread(socket, this);

            sendThread.start();
            recieveThread.start();




        } catch (IOException e) {
            System.out.println("Cannot connect to server");
            e.printStackTrace();
        }

    }






}
