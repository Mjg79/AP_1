package Controller;

import java.io.IOException;
import java.net.Socket;

public class ClientController extends Controller {
    private int port = 8060;
    private Socket clientSocket;
    private String name;

    public ClientController(Socket clientSocket, String name) throws IOException {
      this.clientSocket = clientSocket;
      this.name = name;
    }

    public Socket getClientSocket() {
        return  clientSocket;
    }

    public String getName() {
        return name;
    }
}
