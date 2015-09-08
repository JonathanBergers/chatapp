package server;


import model.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 4-9-15.
 */
public class Server {

    private static final int PORT = 2000;
    private static final String HOST = "localhost";
    private  List<HandleMessageThread> clientThreads = new ArrayList<HandleMessageThread>();


    // run the server
    public static void main(String[] args) {

        Server s = new Server();
    }


    /**constructor
     *
     */
    public Server() {
        run();
    }

    public void run(){

            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                System.out.println("server socket initialized");

                // wait for connection then add thread
                while(true) {
                    Socket socket = serverSocket.accept();

                    System.out.println("Client socket accepted");

                    HandleMessageThread handleMessageThread = new HandleMessageThread(socket, this);
                    handleMessageThread.start();


                    clientThreads.add(handleMessageThread);



                    System.out.println("client socket connection established");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }












    public void broadcastMessage(final Message message){


        for(HandleMessageThread h: clientThreads){

                h.sendMessage(message);

        }
    }

    public void broadcastMessage(final Message message, final String recipent){


        for(HandleMessageThread h: clientThreads){

            if(h.fromUser(recipent)){
                h.sendMessage(message);

            }


        }
    }


}
