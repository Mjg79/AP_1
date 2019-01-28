package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerController extends Controller {
    private ServerSocket server = new ServerSocket(8050);
    private String ip;
    private HashMap<String, Socket> clients = new HashMap<>();

    public ServerController(String ip) throws IOException {
        this.ip = ip;
    }

    public ServerSocket getServer() {
        return server;
    }

    public String getIp() {
        return ip;
    }

    public void joinToServer(String name) throws IOException {
        Socket socket = server.accept();
        clients.put(name, socket);
    }

    public HashMap<String, Socket> getClients() {
        return clients;
    }
}
