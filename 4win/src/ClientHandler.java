import java.net.Socket;

public class ClientHandler extends Communication {

    public ClientHandler(Socket socket) {
        super(socket);
        online = true;
    }

    @Override
    public void init() {

    }
}
