package client;

import java.io.*;
import java.net.Socket;

/**
 * Created by jonathan on 4-9-15.
 * Ontvangt de berichten van de server en geeft deze weer
 */
public class ClientRecieveThread extends Thread{

    private Socket socket;
    private BufferedInputStream inputStream;

    public ClientRecieveThread(Socket socket) {
        this.socket = socket;
        try {
            inputStream = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {






        System.out.println("recieve thread initialized");
        // leest van de socket en print het
        byte[] buffer = new byte[1024];
        int read;
        try {
            while((read = socket.getInputStream().read(buffer)) != -1) {
                String output = new String(buffer, 0, read);
                System.out.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//
//        String userInput;
//        while ((userInput = stdIn.readLine()) != null) {
//            out.println(userInput);
//            System.out.println("echo: " + in.readLine());
//        }


    }
}
