package server;

import model.Message;
import model.User;

import java.io.*;
import java.net.Socket;

/**
 * Created by jonathan on 4-9-15.
 */
public class HandleMessageThread extends Thread {


    private  Socket clientsocket;
    private  Server server;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;


    private User sender;

    public HandleMessageThread(Socket clientsocket, Server server) {
        this.clientsocket = clientsocket;
        this.server = server;

    }

    @Override
    public void run() {

        System.out.println("message handle thread initialized");





        try {
            inputStream = new ObjectInputStream(clientsocket.getInputStream());

            System.out.println("HandleMessageThread: inputstream initialized");
        } catch (IOException e) {
            System.out.println("handle messge thread error: cant initialize object inputstream");
            e.printStackTrace();
        }


        try {
            outputStream = new ObjectOutputStream(clientsocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("handle messge thread error: cant initialize object outputstream");
            e.printStackTrace();
        }





        while(true){

            try {
                Message m = (Message) inputStream.readObject();

                System.out.println("message recieved" + m);

                // eerste bericht
                if(sender == null){

                    assert m.getRecipent() == null: "init message cant be to a user";
                    assert m.getSender() != null: "bericht heeft geen zender ?";

                    // initialize sender, so messages can be send to him
                    sender = m.getSender();

                }

                // bericht heeft een bestemming
                if(m.getRecipent() != null){

                    server.broadcastMessage(m, m.getRecipent());

                }else {
                    // stuur naar iedereen
                    server.broadcastMessage(m);

                }





            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Handlemessagethread: Could not make message object of the inputstream");
                e.printStackTrace();
            }

        }




        // wacht op berichten van user
        // stuur bericht naar server





        
    }


    public void sendMessage(Message message){


        System.out.println("Message handled, and sent");

        try {
            outputStream.writeObject(message);
        } catch (IOException e) {

            System.out.println("Handlemessagethread error: Cant write message object to outputstream");
            e.printStackTrace();
        }



    }

    /**checks if this thread handles message from the given user
     *
     * @param
     * @return
     */
    public boolean fromUser(final String username){


        assert sender != null: "geen init message ??";


        return sender.isUser(username);

    }
}
