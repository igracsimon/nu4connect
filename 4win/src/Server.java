import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    private ServerSocket serverSocket;

    private Socket socket = new Socket();

    private ServerControl Scontrol;



    public Server(ServerControl control){
        this.Scontrol = control;
        try {
            serverSocket = new ServerSocket(5050); //TODO: PORT??
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * Mit dieser Funktion kannst du dem Server beitreten
     */
    public void startServer(){
        for (int i = 0; i <2 ; i++) {
            try {
                socket = serverSocket.accept();
                Scontrol.addClienthandler(new ClientHandler(socket));
                new Thread(Scontrol.getClienthandler(i)::init).start();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Beide clients sind da");
        }
    }


    }

