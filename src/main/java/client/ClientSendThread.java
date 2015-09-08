package client;

import model.Message;
import model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by jonathan on 4-9-15.
 * Wacht voor berichten en stuurt deze door naar de server
 */
public class ClientSendThread extends Thread {


    private final Socket socket;
    private final User user;
    private ObjectOutputStream outputStream;


    public ClientSendThread(Socket socket, User user) {
        this.user = user;
        this.socket = socket;


    }

    @Override
    public void run() {

        //initialize
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Client send thread error: cant initialize object outputstream");
            e.printStackTrace();
        }

        System.out.println("Client send thread initialized");


        //Send first message to let the server know what user has joined the chatroom
        Message initMessage = new Message(user, user + " has joined the room");
        try {
            outputStream.writeObject(initMessage);
        } catch (IOException e) {

            System.out.println("Clientsendthread error:cant write init message");
            e.printStackTrace();
        }





        // read user input and send message
        Scanner in = new Scanner(System.in);

        while(in.hasNextLine()){


            String message = in.nextLine();
            // kijkt of bericht naar een gebruiker moet

            Message m = null;
            if(message.startsWith("@")){

                String recipent = message.substring(1, message.indexOf(" "));
                System.out.println("Message to : " + recipent);
                m = new Message(user, message, recipent);
            }else{
                m = new Message(user, message);
            }

            try {
                outputStream.writeObject(m);
            } catch (IOException e) {

                System.out.println("Clientsendthread error: Cant write message to object outputstream");
                e.printStackTrace();
            }
        }

    }







}
