package client;

import model.Message;
import model.User;

import java.io.*;
import java.net.Socket;

/**
 * Created by jonathan on 4-9-15.
 * Ontvangt de berichten van de server en geeft deze weer
 */
public class ClientRecieveThread extends Thread{

    private final Socket socket;
    private final User user;

    private ObjectInputStream inputStream;


    /**initializes the inputstream
     *
     * @param socket
     * @param user
     */
    public ClientRecieveThread(final Socket socket, final User user) {
        this.user = user;
        this.socket = socket;

    }


    @Override
    public void run() {


        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println("recieve thread initialized");


        while(true){

            try {
                Message m = (Message) inputStream.readObject();

                // kijk of bericht van gebruiker komt
                if(m.getRecipent() != null){

                    System.out.println("Prive bericht van "+ m.getRecipent() + " : "+ m);
                }else{
                    System.out.println(m);
                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("Could not make message object of the inputstream");
                e.printStackTrace();
            }

        }

    }
}
