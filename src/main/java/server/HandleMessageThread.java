package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by jonathan on 4-9-15.
 */
public class HandleMessageThread extends Thread {


    private  Socket clientsocket;
    private  Server server;

    private BufferedInputStream inputStream;
    private BufferedOutputStream outputStream;



    public HandleMessageThread(Socket clientsocket, Server server) {
        this.clientsocket = clientsocket;
        this.server = server;

        try {
            inputStream = new BufferedInputStream(clientsocket.getInputStream());
            outputStream = new BufferedOutputStream(clientsocket.getOutputStream());
        } catch (IOException e) {

            System.out.println("handle messge thread error declaring input or output stream");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        System.out.println("message handle thread initialized");

        server.broadcastMessage("message from message handler thread server");

        // leest van de socket en print het
        byte[] buffer = new byte[1024];
        int read;
        try {
            while((read = clientsocket.getInputStream().read(buffer)) != -1) {
                String output = new String(buffer, 0, read);
                server.broadcastMessage(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // wacht op berichten van user
        // stuur bericht naar server





        
    }


    public void sendMessage(String message){


        System.out.println("Message handled, and sent");

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(clientsocket.getOutputStream());
            printWriter.write(message);
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
