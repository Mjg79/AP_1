package Controller;

import java.io.IOException;
import java.net.Socket;

public class ClientController extends Controller {
    private Socket clientSocket;
    private Profile profile;

    public ClientController(Socket clientSocket, Profile profile) throws IOException {
      this.clientSocket = clientSocket;
      this.profile = profile;
    }

    public Socket getClientSocket() {
        return  clientSocket;
    }

    public Profile getProfile() {
        return profile;
    }
}
