import java.io.*;
import java.net.*;


public class Client {

    private BufferedReader reader;
    private PrintWriter writer;
    private Socket sock;

    public Client(){
        installNetwork();
        Thread readerThread = new Thread(new InputReader()); //TODO
        readerThread.start();
    }

    private void installNetwork(){
        try{
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer= new PrintWriter(sock.getOutputStream());
            System.out.println("Netzwerkverbindung steht!");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private byte[] getMessage(InputStream inputStream) throws IOException
    {
        byte[] len = new byte[4];
        if (!tryReadUntilBufferFull(inputStream, len))
            throw new IOException();
        byte[] message = new byte[len[0] << 24 | len[1] << 16 | len[2] << 8 | len[3]];
        if (!tryReadUntilBufferFull(inputStream, message))
            throw new IOException();

        return message;
    }

    private boolean tryReadUntilBufferFull(InputStream inputStream, byte[] buffer)
    {
        if (buffer.length == 0)
            return false;
        try
        {
            int read = 0;
            while (read < buffer.length)
            {
                int readBytes = inputStream.read(buffer, read, buffer.length - read);
                if (readBytes == -1)
                    return false;
                read += readBytes;
            }
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    public class InputReader implements Runnable{
        @Override

        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println("gelesen: " + message);
                    input.append(message + "\n"); //TODO create input equivalent
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}