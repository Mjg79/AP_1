package Controller;

import Data.DataReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerController extends Controller {
    private ServerSocket server;
    private final static String ip = "localhost";
    private HashMap<Profile, Socket> clients = new HashMap<>();
    private boolean stop = false;

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

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void joinToServer() throws IOException, ClassNotFoundException {
//        while (!stop) {
            System.out.println("server waited...");
//            Socket client = server.accept();
//            ObjectInputStream objectReader = new ObjectInputStream(client.getInputStream());
//            Profile profile = (Profile)objectReader.readObject();
            System.out.println("connected...");
//            clients.put(profile, client);
//        }
    }


    public HashMap<Profile, Socket> getClients() {
        return clients;
    }
}
