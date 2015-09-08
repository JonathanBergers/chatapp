package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jonathan on 4-9-15.
 * Wacht voor berichten en stuurt deze door naar de server
 */
public class ClientSendThread extends Thread {


    private Socket socket;
    private PrintWriter printWriter;
    private Client client;


    public ClientSendThread(Socket socket, Client client) {
        this.client = client;
        this.socket = socket;
        try {
            printWriter = new PrintWriter(socket.getOutputStream());



        } catch (IOException e) {
            System.out.println("Client send thread error: cant initialize printwriter");
            e.printStackTrace();
        }
        System.out.println("Client send thread initialized");
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);

        while(in.hasNextLine()){
            sendMessage(in.next());

        }

    }

    public void sendMessage(String message){


        printWriter.write(client.getUsername() + ": "+ message);
        printWriter.flush();

    }

}
