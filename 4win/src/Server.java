import java.io.*;
import java.net.ServerSocket;
import java.net.Socket ;
import java.util.ArrayList;
import java.util.Iterator;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Server {

    Socket server = new Socket();
    private ArrayList clientStreams;

    public void addLenAndSendMessage(byte[] message) {

        try {
            byte[] len = ByteBuffer.allocate(4)
                    .putInt(message.length)
                    .array();
            byte[] out = ByteBuffer.allocate(len.length + message.length)
                    .put(len)
                    .put(message)
                    .array();

            server.getOutputStream().write(out);
        } catch (IOException e) {
            System.out.println("Could not send Message to " + server.getInetAddress().getHostName());
        }
    }

    public void start() {
        clientStreams = new ArrayList();

        try {
            ServerSocket serverSocket = new ServerSocket(5000); //TODO

            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientStreams.add(writer);
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Habe eine Verbindung");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String pMessage){
            Iterator it = clientStreams.iterator();
            while(it.hasNext()){
                try{
                    PrintWriter writer = (PrintWriter) it.next();
                    writer.println(pMessage);
                    writer.flush();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
    }

    public class ClientHandler implements Runnable{

        private BufferedReader reader;
        private Socket sock;

        public ClientHandler(Socket pClientSocket){
            try{
                sock = pClientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override

        public void run(){
            String message;
            try {
                while ((message = reader.readLine()) != null){
                    System.out.println("gelesen (s): " + message);
                    sendToAll(message);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}

