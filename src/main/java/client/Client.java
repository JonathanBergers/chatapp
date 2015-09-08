package client;

import model.User;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by jonathan on 4-9-15.
 * maakt connectie met server, daarna start deze threads waarin gecommuniceerd kan worden met de server.
 */
public class Client {


    private static final String HOST_NAME = "localhost";
    private static final int HOST_PORT = 2000;


    private ClientRecieveThread recieveThread;
    private ClientSendThread sendThread;

    private final User user;


    // START CLIENT
    public static void main(String[] args) {

        User randomUser = new User("User" + Math.random());
        Client client = new Client(randomUser);
        client.run();

    }


    public Client(User user) {
        this.user = user;
    }

    private void run(){
        try {

            // connect to server
            Socket socket = new Socket(HOST_NAME, HOST_PORT);
            System.out.println("Client: socket connection established with server");

            // start the threads
            recieveThread = new ClientRecieveThread(socket, user);
            sendThread = new ClientSendThread(socket, user);



            sendThread.start();
            recieveThread.start();






        } catch (IOException e) {
            System.out.println("Cannot connect to server");
            e.printStackTrace();
        }

    }






}
