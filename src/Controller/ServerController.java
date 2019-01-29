package Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerController extends Controller {
    private ServerSocket server;
    private final static String ip = "localhost";
    private HashMap<String, Socket> clients = new HashMap<>();


    public ServerController() throws IOException {
    }


    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public String getIp() {
        return ip;
    }

    public void joinToServer() throws IOException {
            System.out.println("server is waiting...");
            Socket client = server.accept();
            System.out.println("connected...");
//            clients.put(name, client);
    }


    public HashMap<String, Socket> getClients() {
        return clients;
    }
}
