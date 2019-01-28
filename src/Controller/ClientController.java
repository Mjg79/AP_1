package Controller;

import java.io.IOException;
import java.net.Socket;

public class ClientController extends Controller {
    private int port = 8060;
    private Socket clientSocket;
    private String name;

    public ClientController(String serverIP, int serverPort, String name) throws IOException {
      clientSocket = new Socket(serverIP, serverPort);
      this.name = name;
    }

    public Socket getClientSocket() {
        return  clientSocket;
    }

    public String getName() {
        return name;
    }
}
